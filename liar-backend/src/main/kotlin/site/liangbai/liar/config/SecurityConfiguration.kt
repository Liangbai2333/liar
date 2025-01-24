package site.liangbai.liar.config

import jakarta.annotation.Resource
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import site.liangbai.liar.entity.Result
import site.liangbai.liar.entity.Result.Companion.success
import site.liangbai.liar.entity.vo.response.auth.AuthorizeVO
import site.liangbai.liar.filter.JwtAuthenticationFilter
import site.liangbai.liar.filter.RequestLogFilter
import site.liangbai.liar.service.auth.AccountContextService
import site.liangbai.liar.util.JwtUtils
import java.io.IOException

@Configuration
class SecurityConfiguration {
    @Resource
    lateinit var jwtAuthenticationFilter: JwtAuthenticationFilter

    @Resource
    lateinit var accountContextService: AccountContextService

    @Resource
    lateinit var utils: JwtUtils

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .authorizeHttpRequests { conf ->
                conf.requestMatchers("/api/admin/**").authenticated()
                    .requestMatchers("/api/**").permitAll()
                    .anyRequest().permitAll()
            }
            .formLogin { conf ->
                conf.loginProcessingUrl("/api/admin/auth/login")
                    .failureHandler(this::handleProcess)
                    .successHandler(this::handleProcess)
                    .permitAll()
            }
            .logout { conf ->
                conf.logoutUrl("/api/admin/auth/logout")
                    .logoutSuccessHandler(this::onLogoutSuccess)
            }
            .exceptionHandling { conf ->
                conf.accessDeniedHandler(this::handleProcess)
                conf.authenticationEntryPoint(this::handleProcess)
            }
            .csrf { it.disable() }
            .sessionManagement { conf ->
                conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .addFilterBefore(RequestLogFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(JwtAuthenticationFilter(), RequestLogFilter::class.java)
            .build()
    }

    /**
     * 将多种类型的Handler整合到同一个方法中，包含：
     * - 登录成功
     * - 登录失败
     * - 未登录拦截/无权限拦截
     * @param request 请求
     * @param response 响应
     * @param exceptionOrAuthentication 异常或是验证实体
     * @throws IOException 可能的异常
     */
    @Throws(IOException::class)
    private fun handleProcess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exceptionOrAuthentication: Any
    ) {
        response.contentType = "application/json;charset=utf-8"
        val writer = response.writer
        if (exceptionOrAuthentication is AccessDeniedException) {
            writer.write(
                Result.forbidden(exceptionOrAuthentication.message!!).asJsonString()
            )
        } else if (exceptionOrAuthentication is Exception) {
            writer.write(
                Result.unauthorized(exceptionOrAuthentication.message!!).asJsonString()
            )
        } else if (exceptionOrAuthentication is Authentication) {
            val user = exceptionOrAuthentication.principal as User
            val account = accountContextService.currentAccount!!
            val jwt = utils.createJwt(user, account.username!!, account.id!!)
            if (jwt == null) {
                writer.write(Result.forbidden("登录验证频繁，请稍后再试").asJsonString())
            } else {
                writer.write(
                    success(
                        AuthorizeVO.fromEntity(account)!!
                            .apply {
                                this.token = jwt
                                this.expire = utils.expireTime()
                            }
                    ).asJsonString()
                )
                writer.write(
                    success(
                    AuthorizeVO.fromEntity(account)!!
                        .apply {
                            this.token = jwt
                            this.expire = utils.expireTime()
                        }
                ).asJsonString())
            }
        }
    }

    /**
     * 退出登录处理，将对应的Jwt令牌列入黑名单不再使用
     * @param request 请求
     * @param response 响应
     * @param authentication 验证实体
     * @throws IOException 可能的异常
     */
    @Throws(IOException::class)
    private fun onLogoutSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        response.contentType = "application/json;charset=utf-8"
        val writer = response.writer
        val authorization = request.getHeader("Authorization")
        if (utils.invalidateJwt(authorization)) {
            writer.write(success("退出登录成功").asJsonString())
            return
        }
        writer.write(Result.failure(400, "退出登录失败").asJsonString())
    }
}