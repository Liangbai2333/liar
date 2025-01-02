package site.liangbai.liar.entity.dto.article

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.util.*

@TableName("tags")
data class Tag(
    @TableId(type = IdType.AUTO)
    var id: Int? = null,
    var name: String? = null,
    var createTime: Date? = null,
    var updateTime: Date? = null
)