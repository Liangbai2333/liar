package site.liangbai.liar.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import site.liangbai.liar.entity.dto.article.Article
import site.liangbai.liar.mapper.article.ArticleMapper
import site.liangbai.liar.service.ArticleService
import site.liangbai.liar.service.CategoryService

@Service
class ArticleServiceImpl(
    private val articleMapper: ArticleMapper,
    private val categoryService: CategoryService
) : ServiceImpl<ArticleMapper, Article>(), ArticleService {
    override fun saveArticle(article: Article) {
        article.category?.let {
            if (it.id == null) {
                categoryService.save(it)
            } else {
                categoryService.updateById(it)
            }
        }
        save(article)
    }

    override fun updateArticle(article: Article) {
        article.category?.let {
            categoryService.updateById(it)
        }
        updateById(article)
    }

    override fun getArticleById(id: Int): Article {
        return articleMapper.getArticleById(id)
    }
}