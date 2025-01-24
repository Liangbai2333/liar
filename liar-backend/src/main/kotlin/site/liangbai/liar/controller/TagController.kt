package site.liangbai.liar.controller

import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import site.liangbai.liar.entity.Result
import site.liangbai.liar.entity.vo.response.article.TagVO
import site.liangbai.liar.service.article.TagService

@RestController
@RequestMapping("api/tag")
class TagController {
    @Resource
    lateinit var tagService: TagService

    @GetMapping("list")
    fun getTagList(): Result<List<TagVO>> {
        return tagService.getTagList().run { Result.success(this) }
    }

    @GetMapping("count")
    fun getTagCount(): Result<Long> {
        return tagService.count().run { Result.success(this) }
    }
}