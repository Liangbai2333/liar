package site.liangbai.liar.filter

import jakarta.annotation.Resource
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import site.liangbai.liar.util.Const
import site.liangbai.liar.util.JwtUtils
import site.liangbai.liar.util.delegate.bean
import java.io.IOException

/**
 * 用于对请求头中Jwt令牌进行校验的工具，为当前请求添加用户验证信息
 * 并将用户的ID存放在请求对象属性中，方便后续使用
 */
@Component
class JwtAuthenticationFilter : OncePerRequestFilter() {
    // ??为什么不能注入
    private val jwtUtils: JwtUtils by bean()

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorization = request.getHeader("Authorization")
        jwtUtils.resolveJwt(authorization)?.let { jwt ->
            val user: UserDetails = jwtUtils.toUser(jwt)
            val authentication =
                UsernamePasswordAuthenticationToken(user, null, user.authorities)
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authentication
            request.setAttribute(Const.ATTR_USER_ID, jwtUtils.toId(jwt))
        }
        filterChain.doFilter(request, response)
    }
}