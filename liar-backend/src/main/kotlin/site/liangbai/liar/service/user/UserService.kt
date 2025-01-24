package site.liangbai.liar.service.user

import com.baomidou.mybatisplus.extension.service.IService
import org.springframework.web.multipart.MultipartFile
import site.liangbai.liar.entity.dto.user.User
import site.liangbai.liar.entity.vo.response.user.UserVO

interface UserService : IService<User> {
    fun getUserList(): List<UserVO>

    fun saveUser(
        id: Int,
        name: String,
        motto: String,
        description: String,
        status: String
    ): Boolean

    fun changePassword(
        id: Int,
        oldPassword: String,
        newPassword: String
    ): String

    fun uploadAvatar(file: MultipartFile): String
}