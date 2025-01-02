package site.liangbai.liar.entity.dto.article

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.time.LocalDateTime

@TableName("categories")
data class Category(
    @TableId(type = IdType.AUTO)
    var id: Int? = null,
    var name: String? = null,
    var description: String? = null,
    var icon: String? = null,
    var color: String? = null,
    var createTime: LocalDateTime? = null,
    var updateTime: LocalDateTime? = null
)
