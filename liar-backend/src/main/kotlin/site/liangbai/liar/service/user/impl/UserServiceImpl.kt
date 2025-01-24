package site.liangbai.liar.service.user.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import site.liangbai.liar.entity.dto.user.User
import site.liangbai.liar.entity.vo.response.user.UserVO
import site.liangbai.liar.mapper.user.UserMapper
import site.liangbai.liar.service.user.UserService

@Service
class UserServiceImpl : ServiceImpl<UserMapper, User>(), UserService {
    override fun getUserList(): List<UserVO> {
        return list().map { UserVO.internalTransform(it) }
    }
}