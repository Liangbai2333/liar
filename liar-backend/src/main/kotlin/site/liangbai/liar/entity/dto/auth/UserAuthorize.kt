package site.liangbai.liar.entity.dto.auth

import com.baomidou.mybatisplus.annotation.TableName

@TableName("users")
data class UserAuthorize(
    var id: Int? = null,
    var username: String? = null,
    var password: String? = null,
    var roles: String? = null
)
