package site.liangbai.liar.controller

import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import site.liangbai.liar.entity.Result
import site.liangbai.liar.entity.vo.response.user.UserVO
import site.liangbai.liar.service.user.UserService

@RestController
@RequestMapping("api/user")
class UserController {
    @Resource
    lateinit var userService: UserService

    @GetMapping("list")
    fun getUserList(): Result<List<UserVO>> {
        return Result.success(userService.getUserList())
    }
}