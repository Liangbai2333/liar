package site.liangbai.liar.entity.dto.user

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.util.*

@TableName("users")
data class User(
    @TableId(type = IdType.AUTO)
    var id: Int? = null,
    var username: String? = null,
    var password: String? = null,
    var name: String? = null,
    var avatar: String? = null,
    var motto: String? = null,
    var description: String? = null,
    var status: String? = null,
    var createTime: Date? = null,
    var updateTime: Date? = null
)