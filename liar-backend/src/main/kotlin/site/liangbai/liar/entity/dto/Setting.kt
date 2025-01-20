package site.liangbai.liar.entity.dto

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

@TableName("setting")
data class Setting(
    @TableId(type = IdType.AUTO)
    var id: Int? = null,
    var title: String? = null,
    var headline: String? = null,
    var summary: String? = null,
    var footer: String? = null
)