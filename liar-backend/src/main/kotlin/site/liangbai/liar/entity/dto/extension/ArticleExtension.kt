package site.liangbai.liar.entity.dto.extension

import site.liangbai.liar.entity.dto.article.Article
import site.liangbai.liar.entity.dto.article.Category
import site.liangbai.liar.entity.dto.article.Tag
import site.liangbai.liar.entity.dto.user.User
import site.liangbai.liar.service.ArticleService
import site.liangbai.liar.service.CategoryService
import site.liangbai.liar.service.TagService
import site.liangbai.liar.service.UserService
import site.liangbai.liar.util.delegate.bean

object ArticleExtension : BaseEntityExtension<Article>() {
    private val tagService by bean<TagService>()
    private val categoryService by bean<CategoryService>()
    private val articleService by bean<ArticleService>()
    private val userService by bean<UserService>()

    val Article.category: Category?
        get() = getOrPutValue("category") {
            this.categoryId?.let { categoryService.getById(it) }
        }

    val Article.tags: MutableList<Tag>?
        get() = getOrPutValue("tags") {
            this.id?.let { tagService.getTagsByArticleId(it).toMutableList() }
        }

    val Article.author: User?
        get() = getOrPutValue("author") {
            this.authorId?.let { userService.getById(it) }
        }

    fun Article.saveArticle() {
        articleService.save(this)
    }

    fun Article.updateArticle() {
        articleService.updateById(this)
    }

    /**
     * 因为有数据库级联，此处不做处理.
     */
    fun Article.deleteArticle() {
        articleService.removeById(this.id)
    }
}