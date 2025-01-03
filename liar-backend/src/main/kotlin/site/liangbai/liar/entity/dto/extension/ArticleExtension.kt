package site.liangbai.liar.entity.dto.extension

import jakarta.annotation.Resource
import org.springframework.stereotype.Component
import site.liangbai.liar.entity.dto.article.Article
import site.liangbai.liar.entity.dto.article.Category
import site.liangbai.liar.entity.dto.article.Tag
import site.liangbai.liar.entity.dto.user.User
import site.liangbai.liar.service.ArticleService
import site.liangbai.liar.service.CategoryService
import site.liangbai.liar.service.TagService
import site.liangbai.liar.service.UserService

@Component
object ArticleExtension : BaseEntityExtension<Article>() {
    @Resource
    lateinit var tagService: TagService
    @Resource
    lateinit var categoryService: CategoryService
    @Resource
    lateinit var articleService: ArticleService
    @Resource
    lateinit var userService: UserService

    val Article.category: Category?
        get() = getOrPutValue("category") {
            this.categoryId?.let { categoryService.getById(it) }
        }

    val Article.tags: List<Tag>?
        get() = getOrPutValue("tags") {
            this.id?.let { tagService.getTagsByArticleId(it) }
        }

    val Article.author: User?
        get() = getOrPutValue("author") {
            this.authorId?.let { userService.getById(it) }
        }

    fun Article.saveArticle(saveCategory: Boolean = false, saveTags: Boolean = false, saveUser: Boolean = false) {
        if (saveCategory) {
            this.category?.let { categoryService.save(it) }
        }
        if (saveTags) {
            this.tags?.forEach { tagService.save(it) }
        }
        if (saveUser) {
            this.author?.let { userService.save(it) }
        }
        articleService.save(this)
    }

    fun Article.updateArticle(updateCategory: Boolean = false, updateTags: Boolean = false, updateUser: Boolean = false) {
        if (updateCategory) {
            this.category?.let { categoryService.updateById(it) }
        }
        if (updateTags) {
            this.tags?.forEach { tagService.updateById(it) }
        }
        if (updateUser) {
            this.author?.let { userService.updateById(it) }
        }
        articleService.updateById(this)
    }

    /**
     * 因为有数据库级联，此处不做处理.
     */
    fun Article.deleteArticle() {
        articleService.removeById(this.id)
    }
}