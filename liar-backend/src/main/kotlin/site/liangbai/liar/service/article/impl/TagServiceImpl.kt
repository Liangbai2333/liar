package site.liangbai.liar.service.article.impl

import com.baomidou.mybatisplus.core.toolkit.Wrappers
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import jakarta.annotation.Resource
import org.springframework.stereotype.Service
import site.liangbai.liar.entity.dto.article.ArticleTag
import site.liangbai.liar.entity.dto.article.Tag
import site.liangbai.liar.entity.vo.response.article.TagVO
import site.liangbai.liar.mapper.article.ArticleTagMapper
import site.liangbai.liar.mapper.article.TagMapper
import site.liangbai.liar.service.article.TagService

@Service
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

    override fun getTagByName(name: String): Tag? {
        return query().eq("name", name).one()
    }

    override fun getTagById(id: Int): Tag? {
        return baseMapper.selectById(id)
    }

    override fun getTagList(): List<TagVO> {
        return lambdaQuery().list()?.map { TagVO.internalTransform(it) } ?: emptyList()
    }

    override fun saveTagListForArticleId(articleId: Int, tagIds: List<Int>) {
        articleTagMapper.delete(Wrappers.query<ArticleTag>().eq("article_id", articleId))
        tagIds.forEach {
            articleTagMapper.insert(ArticleTag(articleId, it))
        }
    }

    override fun saveTag(id: Int, name: String): Boolean {
        val tag = if (id == 0) {
            Tag().apply {
                this.name = name
            }
        } else {
            baseMapper.selectById(id)
        } ?: return false

        tag.name = name
        return saveOrUpdate(tag)
    }

    override fun deleteTag(id: Int): Boolean {
        return removeById(id)
    }
}