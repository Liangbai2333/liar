package site.liangbai.liar.controller

import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import site.liangbai.liar.entity.Result
import site.liangbai.liar.entity.vo.response.article.CategoryVO
import site.liangbai.liar.service.CategoryService

@RestController
@RequestMapping("api/category")
class CategoryController {
    @Resource
    lateinit var categoryService: CategoryService

    @GetMapping("list")
    fun getCategoryList(): Result<List<CategoryVO>> {
        return categoryService.getCategoryList().run { Result.success(this) }
    }
}