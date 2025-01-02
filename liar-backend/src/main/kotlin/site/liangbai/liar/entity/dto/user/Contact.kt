package site.liangbai.liar.entity.dto.user

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.util.*

@TableName("contacts")
data class Contact(
    @TableId(type = IdType.AUTO)
    var id: Int? = null,
    var userId: Int? = null,
    var type: String? = null,
    var icon: String? = null,
    var name: String? = null,
    var value: String? = null,
    var link: String? = null,
    var createTime: Date? = null,
    var updateTime: Date? = null
)
