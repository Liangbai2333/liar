package site.liangbai.liar.service.article

import com.baomidou.mybatisplus.extension.service.IService
import site.liangbai.liar.entity.dto.article.Tag
import site.liangbai.liar.entity.vo.response.article.TagVO

interface TagService : IService<Tag> {
    fun getTagsByArticleId(articleId: Int): List<Tag>

    fun getTagByName(name: String): Tag?

    fun getTagById(id: Int): Tag?

    fun getTagList(): List<TagVO>

    fun saveTagListForArticleId(articleId: Int, tagIds: List<Int>)

    fun saveTag(
        id: Int,
        name: String
    ): Boolean

    fun deleteTag(id: Int): Boolean
}