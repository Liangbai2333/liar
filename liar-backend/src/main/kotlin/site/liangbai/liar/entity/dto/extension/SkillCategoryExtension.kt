package site.liangbai.liar.entity.dto.extension

import site.liangbai.liar.entity.dto.user.SkillCategory
import site.liangbai.liar.entity.dto.user.User
import site.liangbai.liar.service.user.UserService
import site.liangbai.liar.util.delegate.bean

object SkillCategoryExtension : BaseEntityExtension<SkillCategory>() {
    private val userService by bean<UserService>()

    val SkillCategory.user: User?
        get() = getOrPutValue("user") {
            this.userId?.let { userService.getById(it) }
        }
}