package site.liangbai.liar.controller.admin

import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import site.liangbai.liar.entity.Result
import site.liangbai.liar.service.ArticleService

@RestController
@RequestMapping("api/admin/article")
class ArticleAdminController {
    @Resource
    lateinit var articleService: ArticleService

    @PostMapping("save/{id}")
    fun saveArticle(
        @PathVariable("id") id: Int,
        @RequestParam(required = false) title: String?,
        @RequestParam(required = false) state: String?,
        @RequestParam(required = false) summary: String?,
        @RequestParam(required = false) motto: String?,
        @RequestParam(required = false) content: String?,
        @RequestParam(required = false) cover: String?,
        @RequestParam(defaultValue = "0") categoryId: Int,
        @RequestParam(required = false) tags: List<Int>?,
        @RequestParam(defaultValue = "100") priority: Int,
        @RequestParam(defaultValue = "0") authorId: Int
    ): Result<Boolean> {
        return Result.success(
            articleService.saveArticle(
                id,
                title,
                state,
                summary,
                motto,
                content,
                cover,
                categoryId,
                tags,
                priority,
                authorId
            )
        )
    }

    @PostMapping("delete/{id}")
    fun deleteArticle(@PathVariable("id") id: Int): Result<Boolean> {
        return Result.success(articleService.deleteArticle(id))
    }

    @PostMapping("upload/cover")
    fun uploadCover(@RequestParam file: MultipartFile): Result<String> {
        return Result.success(articleService.uploadCoverImage(file), "success")
    }
}