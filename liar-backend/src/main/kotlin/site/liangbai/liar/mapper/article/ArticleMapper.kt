package site.liangbai.liar.mapper.article

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Param
import site.liangbai.liar.entity.dto.article.Article

interface ArticleMapper : BaseMapper<Article> {
    fun getArticleById(@Param("id") id: Int): Article
}