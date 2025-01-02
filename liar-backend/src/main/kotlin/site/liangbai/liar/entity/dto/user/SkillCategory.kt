package site.liangbai.liar.entity.dto.user

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.util.*

@TableName("skill_categories")
data class SkillCategory(
    @TableId(type = IdType.AUTO)
    var id: Int? = null,
    var userId: Int? = null,
    var name: String? = null,
    var createTime: Date? = null,
    var updateTime: Date? = null
)
