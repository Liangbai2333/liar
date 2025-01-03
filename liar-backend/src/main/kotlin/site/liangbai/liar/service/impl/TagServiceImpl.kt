package site.liangbai.liar.service.impl

import com.baomidou.mybatisplus.core.toolkit.Wrappers
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import jakarta.annotation.Resource
import site.liangbai.liar.entity.dto.article.ArticleTag
import site.liangbai.liar.entity.dto.article.Tag
import site.liangbai.liar.mapper.article.ArticleTagMapper
import site.liangbai.liar.mapper.article.TagMapper
import site.liangbai.liar.service.TagService

class TagServiceImpl : ServiceImpl<TagMapper, Tag>(), TagService {
    @Resource
    lateinit var articleTagMapper: ArticleTagMapper

    override fun getTagsByArticleId(articleId: Int): List<Tag> {
        val articleTags = articleTagMapper.selectList(Wrappers.query<ArticleTag>().eq("article_id", articleId))
        if (articleTags.isEmpty()) return emptyList()
        return articleTags.map {
            baseMapper.selectById(it.tagId)
        }
    }
}