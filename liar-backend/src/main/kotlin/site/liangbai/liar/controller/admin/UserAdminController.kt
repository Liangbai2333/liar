package site.liangbai.liar.controller.admin

import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import site.liangbai.liar.entity.Result
import site.liangbai.liar.service.user.UserService

@RestController
@RequestMapping("api/admin/user")
class UserAdminController {
    @Resource
    lateinit var userService: UserService

    @PostMapping("save/{id}")
    fun saveUser(
        @PathVariable("id") id: Int,
        @RequestParam name: String,
        @RequestParam(defaultValue = "") motto: String,
        @RequestParam description: String,
        @RequestParam(defaultValue = "") status: String
    ): Result<Boolean> {
        return Result.success(userService.saveUser(id, name, motto, description, status), "success")
    }

    @PostMapping("change-password/{id}")
    fun changePassword(
        @PathVariable("id") id: Int,
        @RequestParam oldPassword: String,
        @RequestParam newPassword: String
    ): Result<String> {
        return Result.success(userService.changePassword(id, oldPassword, newPassword), "success")
    }

    @PostMapping("upload/avatar")
    fun uploadIcon(@RequestParam file: MultipartFile): Result<String> {
        return Result.success(userService.uploadAvatar(file), "success")
    }
}