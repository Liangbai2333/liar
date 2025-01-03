package site.liangbai.liar.controller

import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.*
import site.liangbai.liar.entity.Result.Companion.successResult
import site.liangbai.liar.service.ArticleService

@RestController("api/article")
class ArticleController {
    @Resource
    lateinit var articleService: ArticleService

    @PostMapping("view/{id}")
    suspend fun viewArticle(@PathVariable("id") id: Int) {
        return articleService.viewArticle(id).run { successResult() }
    }

    @GetMapping("get/{id}")
    fun getArticleById(@PathVariable("id") id: Int) {
        return articleService.getArticleById(id).run { successResult(this) }
    }

    @GetMapping("list")
    fun getArticleList(
        @RequestParam page: Int = 1,
        @RequestParam size: Int = 10,
        @RequestParam categoryId: Int? = null,
        @RequestParam tagId: Int? = null,
        @RequestParam authorId: Int? = null,
        @RequestParam keyword: String? = null,
        @RequestParam sort: String = "priority, create_time",
        @RequestParam order: String = "desc"
    ) {
        return articleService.getArticleList(page, size, categoryId, tagId, authorId, keyword, sort, order).run { successResult(this) }
    }
}