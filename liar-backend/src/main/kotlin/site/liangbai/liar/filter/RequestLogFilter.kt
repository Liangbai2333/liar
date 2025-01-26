package site.liangbai.liar.filter

import com.alibaba.fastjson2.JSONObject
import jakarta.annotation.Resource
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingResponseWrapper
import site.liangbai.liar.util.Const
import site.liangbai.liar.util.SnowflakeIdGenerator
import site.liangbai.liar.util.delegate.bean
import java.io.IOException

/**
 * 请求日志过滤器，用于记录所有用户请求信息
 */
@Component
class RequestLogFilter : OncePerRequestFilter() {
    private val log = LoggerFactory.getLogger(RequestLogFilter::class.java)

    // ??为什么不能注入
    private val generator: SnowflakeIdGenerator by bean()

    private val ignores = setOf("/swagger-ui", "/v3/api-docs")

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (this.isIgnoreUrl(request.servletPath)) {
            filterChain.doFilter(request, response)
        } else {
            val startTime = System.currentTimeMillis()
            this.logRequestStart(request)
            val wrapper = ContentCachingResponseWrapper(response)
            filterChain.doFilter(request, wrapper)
            this.logRequestEnd(wrapper, startTime)
            wrapper.copyBodyToResponse()
        }
    }

    /**
     * 判定当前请求url是否不需要日志打印
     * @param url 路径
     * @return 是否忽略
     */
    private fun isIgnoreUrl(url: String): Boolean {
        for (ignore in ignores) {
            if (url.startsWith(ignore)) return true
        }
        return false
    }

    /**
     * 请求结束时的日志打印，包含处理耗时以及响应结果
     * @param wrapper 用于读取响应结果的包装类
     * @param startTime 起始时间
     */
    fun logRequestEnd(wrapper: ContentCachingResponseWrapper, startTime: Long) {
        val time = System.currentTimeMillis() - startTime
        val status = wrapper.status
        val content = if (status != 200) "$status 错误" else String(wrapper.contentAsByteArray)
        log.info("请求处理耗时: {}ms", time)
    }

    /**
     * 请求开始时的日志打印，包含请求全部信息，以及对应用户角色
     * @param request 请求
     */
    fun logRequestStart(request: HttpServletRequest) {
        val reqId: Long = generator.nextId()
        MDC.put("reqId", reqId.toString())
        val `object` = JSONObject()
        request.parameterMap.forEach { (k: String?, v: Array<String?>?) ->
            `object`[k] = if (v.isNotEmpty()) v[0] else null
        }
        val id = request.getAttribute(Const.ATTR_USER_ID)
        if (id != null) {
            val user = SecurityContextHolder.getContext().authentication.principal as User
            log.info(
                "请求URL: \"{}\" ({}) | 远程IP地址: {} │ 身份: {} (UID: {}) | 角色: {}",
                request.servletPath, request.method, request.remoteAddr,
                user.username, id, user.authorities
            )
        } else {
            log.info(
                "请求URL: \"{}\" ({}) | 远程IP地址: {} │ 身份: 未验证",
                request.servletPath, request.method, request.remoteAddr
            )
        }
    }
}
