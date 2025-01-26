package site.liangbai.liar.controller

import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import site.liangbai.liar.entity.Result
import site.liangbai.liar.entity.vo.response.article.CategoryVO
import site.liangbai.liar.service.article.ArticleService
import site.liangbai.liar.service.article.CategoryService

@RestController
@RequestMapping("api/category")
class CategoryController {
    @Resource
    lateinit var categoryService: CategoryService
    @Resource
    lateinit var articleService: ArticleService

    @GetMapping("list")
    fun getCategoryList(): Result<List<CategoryVO>> {
        return categoryService.getCategoryList().run { Result.success(this) }
    }

    @GetMapping("count")
    fun getCategoryCount(): Result<Long> {
        return categoryService.count().run { Result.success(this) }
    }

    @GetMapping("article-count")
    fun getArticleCountByCategoryId(@RequestParam id: Int): Result<Long> {
        return articleService.query().eq("category_id", id).count().run { Result.success(this) }
    }
}