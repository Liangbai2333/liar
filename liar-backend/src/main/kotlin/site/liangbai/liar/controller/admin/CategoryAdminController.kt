package site.liangbai.liar.controller.admin

import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import site.liangbai.liar.entity.Result
import site.liangbai.liar.service.CategoryService

@RestController
@RequestMapping("api/admin/category")
class CategoryAdminController {
    @Resource
    lateinit var categoryService: CategoryService

    @PostMapping("save/{id}")
    fun saveCategory(
        @PathVariable("id") id: Int,
        @RequestParam name: String,
        @RequestParam(required = false) description: String?,
        @RequestParam(required = false) icon: String?,
        @RequestParam(required = false) color: String?
    ): Result<Boolean> {
        return Result.success(categoryService.saveCategory(id, name, description, icon, color))
    }

    @PostMapping("delete/{id}")
    fun deleteArticle(@PathVariable("id") id: Int): Result<Boolean> {
        return Result.success(categoryService.deleteCategory(id))
    }

    @PostMapping("upload/icon")
    fun uploadIcon(@RequestParam file: MultipartFile): Result<String> {
        return Result.success(categoryService.uploadIconImage(file), "success")
    }
}