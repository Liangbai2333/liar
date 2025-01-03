package site.liangbai.liar.service

import com.baomidou.mybatisplus.extension.service.IService
import site.liangbai.liar.entity.dto.article.Tag

interface TagService : IService<Tag> {
    fun getTagsByArticleId(articleId: Int): List<Tag>
}