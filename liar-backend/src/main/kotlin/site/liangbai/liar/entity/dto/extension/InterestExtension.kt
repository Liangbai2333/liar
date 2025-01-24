package site.liangbai.liar.entity.dto.extension

import site.liangbai.liar.entity.dto.user.Interest
import site.liangbai.liar.entity.dto.user.User
import site.liangbai.liar.service.UserService
import site.liangbai.liar.util.delegate.bean

object InterestExtension : BaseEntityExtension<Interest>() {
    private val userService by bean<UserService>()

    val Interest.user: User?
        get() = getOrPutValue("user") {
            this.userId?.let { userService.getById(it) }
        }
}