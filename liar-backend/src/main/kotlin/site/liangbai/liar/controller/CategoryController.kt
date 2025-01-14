package site.liangbai.liar.controller

import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import site.liangbai.liar.entity.Result.Companion.successResult
import site.liangbai.liar.service.CategoryService

@RestController("api/category")
class CategoryController {
    @Resource
    lateinit var categoryService: CategoryService

    @GetMapping("list")
    fun getCategoryList() {
        return categoryService.getCategoryList().run { successResult(this) }
    }
}