package site.liangbai.liar.entity.dto.auth

import com.baomidou.mybatisplus.annotation.TableName

@TableName("users")
data class UserAuth(
    var id: Int? = null,
    var username: String? = null,
    var password: String? = null
)
