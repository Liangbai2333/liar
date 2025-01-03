package site.liangbai.liar.service

import com.baomidou.mybatisplus.extension.service.IService
import site.liangbai.liar.entity.dto.user.User

interface UserService : IService<User> {
    fun getUserById(id: Int): User
}