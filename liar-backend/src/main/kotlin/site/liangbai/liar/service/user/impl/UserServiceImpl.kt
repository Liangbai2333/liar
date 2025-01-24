package site.liangbai.liar.service.user.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import site.liangbai.liar.entity.dto.user.User
import site.liangbai.liar.entity.vo.response.user.UserVO
import site.liangbai.liar.mapper.user.UserMapper
import site.liangbai.liar.service.system.FileUploadService
import site.liangbai.liar.service.user.UserService
import site.liangbai.liar.util.ifNotEmpty
import java.nio.file.Files
import java.nio.file.Paths

@Service
class UserServiceImpl(
    private val passwordEncoder: PasswordEncoder,
    private val fileUploadService: FileUploadService
) : ServiceImpl<UserMapper, User>(), UserService {
    private val avatarDir = Paths.get("uploads/user/avatar")

    init {
        if (!Files.exists(avatarDir)) {
            Files.createDirectories(avatarDir)
        }
    }
    override fun getUserList(): List<UserVO> {
        return list().map { UserVO.internalTransform(it) }
    }

    override fun saveUser(id: Int, name: String, motto: String, description: String, status: String): Boolean {
        val user = getById(id) ?: return false

        name.ifNotEmpty {
            user.name = name
        }
        motto.ifNotEmpty {
            user.motto = motto
        }
        description.ifNotEmpty {
            user.description = description
        }
        status.ifNotEmpty {
            user.status = status
        }
        return updateById(user)
    }

    override fun changePassword(id: Int, oldPassword: String, newPassword: String): String {
        val user = getById(id) ?: return "用户不存在"
        return if (passwordEncoder.matches(oldPassword, user.password)) {
            user.password = passwordEncoder.encode(newPassword)
            updateById(user)
            "修改成功"
        } else {
            "旧密码错误"
        }
    }

    override fun uploadAvatar(file: MultipartFile): String {
        return fileUploadService.uploadImage(file, avatarDir)
    }
}