package site.liangbai.liar.entity.vo.response.article

import site.liangbai.liar.entity.dto.article.Article
import site.liangbai.liar.entity.dto.extension.ArticleExtension.category
import site.liangbai.liar.entity.dto.extension.ArticleExtension.tags
import site.liangbai.liar.entity.vo.VOTransformer
import java.time.LocalDateTime

data class ArticleVO(
    val id: Int,
    val title: String,
    val summary: String,
    val motto: String?,
    val content: String,
    val cover: String,
    val category: CategoryVO?,
    val tags: List<TagVO>,
    val views: Int,
    val authorId: Int,
    val priority: Int,
    val createTime: LocalDateTime,
    val updateTime: LocalDateTime
) {
    companion object : VOTransformer<Article, ArticleVO> {
        override fun internalTransform(entity: Article): ArticleVO {
            return ArticleVO(
                id = entity.id!!,
                title = entity.title!!,
                summary = entity.summary!!,
                motto = entity.motto,
                content = entity.content!!,
                cover = entity.cover!!,
                category = CategoryVO.fromEntity(entity.category),
                tags = entity.tags?.map { TagVO.internalTransform(it) } ?: emptyList(),
                views = entity.views!!,
                authorId = entity.authorId!!,
                priority = entity.priority!!,
                createTime = entity.createTime!!,
                updateTime = entity.updateTime!!
            )
        }
    }
}