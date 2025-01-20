package site.liangbai.liar.config

import jakarta.annotation.Resource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import site.liangbai.liar.filter.JwtAuthenticationFilter

@Configuration
class SecurityConfiguration {
    @Resource
    lateinit var jwtAuthenticationFilter: JwtAuthenticationFilter

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .authorizeHttpRequests { conf ->
                conf.requestMatchers("/api/admin/**").authenticated()
                    .requestMatchers("/api/**").permitAll()
                    .anyRequest().permitAll()
            }
            .formLogin { conf ->
                conf.loginProcessingUrl("/api/admin/login")

            }
            .csrf { it.disable() }
            .sessionManagement { conf ->
                conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .addFilterBefore(JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
}