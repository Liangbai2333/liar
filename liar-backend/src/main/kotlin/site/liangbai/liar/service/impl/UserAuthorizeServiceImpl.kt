package site.liangbai.liar.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import site.liangbai.liar.entity.dto.auth.UserAuthorize
import site.liangbai.liar.mapper.auth.UserAuthorizeMapper
import site.liangbai.liar.service.UserAuthorizeService

class UserAuthorizeServiceImpl : ServiceImpl<UserAuthorizeMapper, UserAuthorize>(), UserAuthorizeService {
    override fun loadUserByUsername(username: String): UserDetails {
        val userAuth = lambdaQuery().eq(UserAuthorize::username, username).one()
            ?: throw UsernameNotFoundException("用户名或密码不存在")
        return User
            .withUsername(username)
            .password(userAuth.password!!)
            .roles(userAuth.roles!!)
            .build()
    }
}