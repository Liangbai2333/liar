package site.liangbai.liar.util

object Const {
    //JWT令牌
    const val JWT_BLACK_LIST: String = "jwt:blacklist:"
    const val JWT_FREQUENCY: String = "jwt:frequency:"
    //请求频率限制
    const val FLOW_LIMIT_COUNTER: String = "flow:counter:"
    const val FLOW_LIMIT_BLOCK: String = "flow:block:"
    //过滤器优先级
    const val ORDER_FLOW_LIMIT: Int = -101
    const val ORDER_CORS: Int = -102
    //请求自定义属性
    const val ATTR_USER_ID: String = "userId"
}