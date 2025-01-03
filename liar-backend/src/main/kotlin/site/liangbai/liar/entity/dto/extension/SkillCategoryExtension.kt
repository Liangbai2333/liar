package site.liangbai.liar.entity.dto.extension

import jakarta.annotation.Resource
import site.liangbai.liar.entity.dto.user.SkillCategory
import site.liangbai.liar.entity.dto.user.User
import site.liangbai.liar.service.UserService

object SkillCategoryExtension : BaseEntityExtension<SkillCategory>() {
    @Resource
    lateinit var userService: UserService

    val SkillCategory.user: User?
        get() = getOrPutValue("user") {
            this.userId?.let { userService.getById(it) }
        }
}