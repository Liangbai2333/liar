package site.liangbai.liar.entity

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONWriter

data class Result<T>(val code: Int, val data: T? = null, val message: String) {
    /**
     * 快速将当前实体转换为JSON字符串格式
     * @return JSON字符串
     */
    fun asJsonString(): String {
        return JSON.toJSONString(this, JSONWriter.Feature.WriteNulls)
    }

    companion object {
        fun <T> success(data: T?, message: String): Result<T> {
            return Result(200, data, message)
        }

        fun success(message: String): Result<Unit> {
            return success(null, message)
        }

        fun <T> success(data: T?): Result<T> {
            return success(data, "success")
        }

        fun empty(): Result<Unit> {
            return success(null, "success")
        }

        fun failure(code: Int, message: String): Result<Unit> {
            return Result(code, null, message)
        }

        fun forbidden(message: String): Result<Unit> {
            return failure(403, message)
        }

        fun unauthorized(message: String): Result<Unit> {
            return failure(401, message)
        }
    }
}
