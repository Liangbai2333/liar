package site.liangbai.liar.controller.admin

import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.*
import site.liangbai.liar.entity.Result
import site.liangbai.liar.service.TagService

@RestController
@RequestMapping("api/admin/tag")
class TagAdminController {
    @Resource
    lateinit var tagService: TagService

    @PostMapping("save/{id}")
    fun saveTag(
        @PathVariable("id") id: Int,
        @RequestParam name: String
    ): Result<Boolean> {
        return Result.success(tagService.saveTag(id, name))
    }

    @PostMapping("delete/{id}")
    fun deleteTag(@PathVariable("id") id: Int): Result<Boolean> {
        return Result.success(tagService.deleteTag(id))
    }
}