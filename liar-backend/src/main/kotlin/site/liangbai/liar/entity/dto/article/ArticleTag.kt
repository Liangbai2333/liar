package site.liangbai.liar.entity.dto.article

import com.baomidou.mybatisplus.annotation.TableName
import java.time.LocalDateTime

@TableName("article_tags")
data class ArticleTag(
    var articleId: Int? = null,
    var tagId: Int? = null,
    var createTime: LocalDateTime? = null,
)
