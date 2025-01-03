package site.liangbai.liar.service

import com.baomidou.mybatisplus.extension.service.IService
import site.liangbai.liar.entity.dto.article.Article

interface ArticleService : IService<Article> {
    fun getArticleById(id: Int): Article
}