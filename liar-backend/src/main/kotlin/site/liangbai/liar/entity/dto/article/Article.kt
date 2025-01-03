package site.liangbai.liar.entity.dto.article

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.time.LocalDateTime

@TableName("articles")
data class Article(
    @TableId(type = IdType.AUTO)
    var id: Int? = null,
    var title: String? = null,
    var summary: String? = null,
    var motto: String? = null,
    var content: String? = null,
    var cover: String? = null,
    var categoryId: Int? = null,
    var views: Int? = null,
    var authorId: Int? = null,
    var priority: Int? = null,
    var createTime: LocalDateTime? = null,
    var updateTime: LocalDateTime? = null
)