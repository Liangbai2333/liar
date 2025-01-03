package site.liangbai.liar.controller

import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import site.liangbai.liar.entity.Result.Companion.successResult
import site.liangbai.liar.service.TagService

@RestController("api/tag")
class TagController {
    @Resource
    lateinit var tagService: TagService

    @GetMapping("list")
    fun getTagList() {
        return tagService.getTagList().run { successResult(this) }
    }
}