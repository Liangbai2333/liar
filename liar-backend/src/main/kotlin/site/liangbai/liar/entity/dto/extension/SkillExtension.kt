package site.liangbai.liar.entity.dto.extension

import jakarta.annotation.Resource
import site.liangbai.liar.entity.dto.user.Skill
import site.liangbai.liar.entity.dto.user.SkillCategory
import site.liangbai.liar.entity.dto.user.User
import site.liangbai.liar.service.SkillCategoryService
import site.liangbai.liar.service.UserService

object SkillExtension : BaseEntityExtension<Skill>() {
    @Resource
    lateinit var skillCategoryService: SkillCategoryService
    @Resource
    lateinit var userService: UserService

    val Skill.skillCategory: SkillCategory?
        get() = getOrPutValue("skillCategory") {
            this.categoryId?.let { skillCategoryService.getById(it) }
        }

    val Skill.user: User?
        get() = getOrPutValue("user") {
            this.userId?.let { userService.getById(it)}
        }
}