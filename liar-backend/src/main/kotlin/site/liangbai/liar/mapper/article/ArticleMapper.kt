package site.liangbai.liar.mapper.article

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import site.liangbai.liar.entity.dto.article.Article

interface ArticleMapper : BaseMapper<Article> {
    /**
     * 分页查询文章
     *
     * @param page     页码
     * @param size     每页数量
     * @param categoryId 分类id
     * @param tagId    标签id
     * @param authorId 作者id
     * @param keyword  关键字
     * @return 文章列表
     */
    fun pageArticle(
        page: Int,
        size: Int,
        categoryId: Int? = null,
        tagId: Int? = null,
        authorId: Int? = null,
        keyword: String? = null,
        sort: String = "priority, create_time",
        order: String = "desc"
    ): List<Article>
}