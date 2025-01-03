package site.liangbai.liar.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import site.liangbai.liar.entity.dto.user.User
import site.liangbai.liar.mapper.user.UserMapper
import site.liangbai.liar.service.UserService

class UserServiceImpl : ServiceImpl<UserMapper, User>(), UserService {
    override fun getUserById(id: Int): User {
        return getById(id)
    }
}