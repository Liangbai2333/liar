package site.liangbai.liar.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import site.liangbai.liar.entity.dto.article.Article
import site.liangbai.liar.entity.dto.extension.ArticleExtension.saveArticle
import site.liangbai.liar.entity.vo.response.article.ArticleVO
import site.liangbai.liar.entity.vo.response.article.ArticleVO.Companion.fromEntity
import site.liangbai.liar.enum.ArticleState
import site.liangbai.liar.mapper.article.ArticleMapper
import site.liangbai.liar.service.ArticleService
import site.liangbai.liar.service.CategoryService
import site.liangbai.liar.service.TagService
import site.liangbai.liar.util.ifNotEmpty
import site.liangbai.liar.util.ifNotZero

@Service
class ArticleServiceImpl(
    private val articleMapper: ArticleMapper,
    private val categoryService: CategoryService,
    private val tagService: TagService
) : ServiceImpl<ArticleMapper, Article>(), ArticleService {
    override fun getArticleById(id: Int) = fromEntity(getById(id))
    override fun getArticleList(
        page: Int,
        size: Int,
        categoryId: Int?,
        tagId: Int?,
        authorId: Int?,
        keyword: String?,
        sort: String,
        order: String
    ): List<ArticleVO> {
        return articleMapper.pageArticle((page - 1) * size, size, categoryId, tagId, authorId, keyword, sort, order)
            .map { fromEntity(it) ?: throw IllegalStateException("illegal access") }
    }

    override fun saveArticle(
        id: Int,
        title: String?,
        state: String?,
        summary: String?,
        motto: String?,
        content: String?,
        cover: String?,
        categoryId: Int,
        tags: List<Int>?,
        priority: Int,
        authorId: Int
    ): Boolean {
        val article = if (id == 0) {
            Article().apply {
                this.title = title
                this.state = runCatching { ArticleState.valueOf(state ?: "DRAFT") }.getOrNull() ?: ArticleState.DRAFT
                this.summary = summary
                this.motto = motto
                this.content = content
                this.cover = cover
                this.categoryId = categoryId
                this.priority = priority
                this.authorId = authorId
            }
        } else {
            getById(id)
        }

        if (article == null) {
            return false
        }

        title?.let {
            article.title = it
        }
        state?.let {
            article.state = runCatching { ArticleState.valueOf(it) }.getOrNull() ?: ArticleState.DRAFT
        }
        summary?.let {
            article.summary = it
        }
        motto?.let {
            article.motto = it
        }
        content?.let {
            article.content = it
        }
        cover?.let {
            article.cover = it
        }
        categoryId.ifNotZero {
            article.categoryId = categoryId
        }
        priority.ifNotZero {
            article.priority = priority
        }
        authorId.ifNotZero {
            article.authorId = authorId
        }
        if (tags != null) {
            tagService.saveTagListForArticleId(article.id!!, tags)
        }
        article.saveArticle()
        return true
    }

    override fun deleteArticle(id: Int): Boolean {
        val article = getById(id) ?: return false
        article.state = ArticleState.DELETED
        return articleMapper.updateById(article) > 0
    }


    override fun viewArticle(id: Int) {
        val article = getById(id)
        article?.let {
            it.views = it.views!! + 1
            articleMapper.updateById(it)
        }
    }
}