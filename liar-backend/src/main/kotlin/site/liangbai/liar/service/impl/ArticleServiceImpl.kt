package site.liangbai.liar.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import site.liangbai.liar.entity.dto.article.Article
import site.liangbai.liar.entity.vo.response.article.ArticleVO
import site.liangbai.liar.entity.vo.response.article.ArticleVO.Companion.fromEntity
import site.liangbai.liar.mapper.article.ArticleMapper
import site.liangbai.liar.service.ArticleService
import site.liangbai.liar.service.CategoryService

@Service
class ArticleServiceImpl(
    private val articleMapper: ArticleMapper,
    private val categoryService: CategoryService
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
        return articleMapper.pageArticle(page, size, categoryId, tagId, authorId, keyword, sort, order)
            .map { fromEntity(it) ?: throw IllegalStateException("illegal access") }
    }

    override fun viewArticle(id: Int) {
        val article = getById(id)
        article?.let {
            it.views = it.views!! + 1
            articleMapper.updateById(it)
        }
    }
}