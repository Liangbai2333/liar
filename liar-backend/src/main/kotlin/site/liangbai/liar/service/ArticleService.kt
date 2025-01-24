package site.liangbai.liar.service

import com.baomidou.mybatisplus.extension.service.IService
import org.springframework.web.multipart.MultipartFile
import site.liangbai.liar.entity.dto.article.Article
import site.liangbai.liar.entity.vo.response.article.ArticleVO

interface ArticleService : IService<Article> {
    fun getArticleById(id: Int): ArticleVO?

    fun getArticleList(
        page: Int = 1,
        size: Int = 10,
        categoryId: Int? = null,
        tagId: Int? = null,
        authorId: Int? = null,
        keyword: String? = null,
        sort: String = "priority, create_time",
        order: String = "desc"
    ): List<ArticleVO>
    
    fun saveArticle(
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
    ): Boolean

    fun deleteArticle(id: Int): Boolean

    fun viewArticle(id: Int)

    fun uploadCoverImage(
        file: MultipartFile
    ): String
}