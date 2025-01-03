package site.liangbai.liar.entity.dto.extension

import jakarta.annotation.Resource
import site.liangbai.liar.entity.dto.user.Interest
import site.liangbai.liar.entity.dto.user.User
import site.liangbai.liar.service.UserService

object InterestExtension : BaseEntityExtension<Interest>() {
    @Resource
    lateinit var userService: UserService

    val Interest.user: User?
        get() = getOrPutValue("user") {
            this.userId?.let { userService.getUserById(it) }
        }
}