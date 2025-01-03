package site.liangbai.liar.entity.dto.extension

import jakarta.annotation.Resource
import site.liangbai.liar.entity.dto.user.Contact
import site.liangbai.liar.entity.dto.user.User
import site.liangbai.liar.service.UserService

object ContactExtension : BaseEntityExtension<Contact>() {
    @Resource
    lateinit var userService: UserService

    val Contact.user: User?
        get() = getOrPutValue("user") {
            this.userId?.let { userService.getUserById(it) }
        }
}