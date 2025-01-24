package site.liangbai.liar.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import site.liangbai.liar.entity.dto.user.User
import site.liangbai.liar.mapper.user.UserMapper
import site.liangbai.liar.service.UserService

@Service
class UserServiceImpl : ServiceImpl<UserMapper, User>(), UserService {
}