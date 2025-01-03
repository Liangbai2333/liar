package site.liangbai.liar.service

import com.baomidou.mybatisplus.extension.service.IService
import site.liangbai.liar.entity.dto.article.Tag
import site.liangbai.liar.entity.vo.response.article.TagVO

interface TagService : IService<Tag> {
    fun getTagsByArticleId(articleId: Int): List<Tag>

    fun getTagByName(name: String): Tag?

    fun getTagList(): List<TagVO>
}