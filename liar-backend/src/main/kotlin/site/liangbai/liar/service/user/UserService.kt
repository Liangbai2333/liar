package site.liangbai.liar.service.user

import com.baomidou.mybatisplus.extension.service.IService
import site.liangbai.liar.entity.dto.user.User
import site.liangbai.liar.entity.vo.response.user.UserVO

interface UserService : IService<User> {
    fun getUserList(): List<UserVO>
}