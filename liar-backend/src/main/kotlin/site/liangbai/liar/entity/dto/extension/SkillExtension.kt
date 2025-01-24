package site.liangbai.liar.entity.dto.extension

import site.liangbai.liar.entity.dto.user.Skill
import site.liangbai.liar.entity.dto.user.SkillCategory
import site.liangbai.liar.entity.dto.user.User
import site.liangbai.liar.service.user.SkillCategoryService
import site.liangbai.liar.service.user.UserService
import site.liangbai.liar.util.delegate.bean

object SkillExtension : BaseEntityExtension<Skill>() {
    private val skillCategoryService by bean<SkillCategoryService>()
    private val userService by bean<UserService>()

    val Skill.skillCategory: SkillCategory?
        get() = getOrPutValue("skillCategory") {
            this.categoryId?.let { skillCategoryService.getById(it) }
        }

    val Skill.user: User?
        get() = getOrPutValue("user") {
            this.userId?.let { userService.getById(it)}
        }
}