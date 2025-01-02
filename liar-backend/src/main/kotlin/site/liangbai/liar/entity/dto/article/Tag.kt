package site.liangbai.liar.entity.dto.article

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.time.LocalDateTime

@TableName("tags")
data class Tag(
    @TableId(type = IdType.AUTO)
    var id: Int? = null,
    var name: String? = null,
    var createTime: LocalDateTime? = null,
    var updateTime: LocalDateTime? = null
)