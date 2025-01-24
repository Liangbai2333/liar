package site.liangbai.liar.entity.dto.extension

import site.liangbai.liar.entity.dto.user.Contact
import site.liangbai.liar.entity.dto.user.User
import site.liangbai.liar.service.UserService
import site.liangbai.liar.util.delegate.bean

object ContactExtension : BaseEntityExtension<Contact>() {
    private val userService by bean<UserService>()

    val Contact.user: User?
        get() = getOrPutValue("user") {
            this.userId?.let { userService.getById(it) }
        }
}