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
    override fun getArticleById(id: Int): Article {
        return articleMapper.getArticleById(id)
    }
}