package site.liangbai.liar.filter

import jakarta.annotation.Resource
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpFilter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.annotation.Order
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component
import site.liangbai.liar.entity.Result.Companion.forbiddenResult
import site.liangbai.liar.util.Const
import site.liangbai.liar.util.FlowUtils
import java.io.IOException

/**
 * 限流控制过滤器
 * 防止用户高频请求接口，借助Redis进行限流
 */
@Slf4j
@Component
@Order(Const.ORDER_FLOW_LIMIT)
class FlowLimitingFilter : HttpFilter() {
    @Resource
    lateinit var template: StringRedisTemplate

    //指定时间内最大请求次数限制
    @Value("\${spring.web.flow.limit}")
    var limit: Int = 0

    //计数时间周期
    @Value("\${spring.web.flow.period}")
    var period: Int = 0

    //超出请求限制封禁时间
    @Value("\${spring.web.flow.block}")
    var block: Int = 0

    @Resource
    lateinit var utils: FlowUtils

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val address = request.remoteAddr
        if (!tryCount(address)) this.writeBlockMessage(response)
        else chain.doFilter(request, response)
    }

    /**
     * 尝试对指定IP地址请求计数，如果被限制则无法继续访问
     * @param address 请求IP地址
     * @return 是否操作成功
     */
    private fun tryCount(address: String): Boolean {
        synchronized(address.intern()) {
            if (java.lang.Boolean.TRUE == template.hasKey(Const.FLOW_LIMIT_BLOCK + address)) return false
            val counterKey: String = Const.FLOW_LIMIT_COUNTER + address
            val blockKey: String = Const.FLOW_LIMIT_BLOCK + address
            return utils.limitPeriodCheck(counterKey, blockKey, block, limit, period)
        }
    }

    /**
     * 为响应编写拦截内容，提示用户操作频繁
     * @param response 响应
     * @throws IOException 可能的异常
     */
    @Throws(IOException::class)
    private fun writeBlockMessage(response: HttpServletResponse) {
        response.status = HttpServletResponse.SC_FORBIDDEN
        response.contentType = "application/json;charset=utf-8"
        val writer = response.writer
        writer.write(forbiddenResult("操作频繁，请稍后再试").asJsonString())
    }
}
