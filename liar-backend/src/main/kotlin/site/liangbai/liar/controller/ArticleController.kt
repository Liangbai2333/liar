package site.liangbai.liar.controller

import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.*
import site.liangbai.liar.entity.Result
import site.liangbai.liar.entity.Result.Companion.successResult
import site.liangbai.liar.entity.vo.response.article.ArticleVO
import site.liangbai.liar.service.ArticleService


@RestController
@RequestMapping("api/article")
class ArticleController {
    @Resource
    lateinit var articleService: ArticleService

    @PostMapping("view/{id}")
    suspend fun viewArticle(@PathVariable("id") id: Int): Result<Unit> {
        return articleService.viewArticle(id).run { successResult() }
    }

    @GetMapping("get/{id}")
    fun getArticleById(@PathVariable("id") id: Int): Result<ArticleVO> {
        return articleService.getArticleById(id).run { successResult(this) }
    }

    @GetMapping("list")
    fun getArticleList(
        @RequestParam page: Int,
        @RequestParam size: Int,
        @RequestParam(required = false) categoryId: Int?,
        @RequestParam(required = false) tagId: Int?,
        @RequestParam(required = false) authorId: Int?,
        @RequestParam(required = false) keyword: String?,
        @RequestParam(defaultValue = "priority, create_time") sort: String,
        @RequestParam(defaultValue = "desc") order: String
    ): Result<List<ArticleVO>> {
        return articleService.getArticleList(page, size, categoryId, tagId, authorId, keyword, sort, order).run { successResult(this) }
    }
}