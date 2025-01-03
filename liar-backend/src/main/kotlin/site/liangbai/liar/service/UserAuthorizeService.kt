package site.liangbai.liar.service

import com.baomidou.mybatisplus.extension.service.IService
import org.springframework.security.core.userdetails.UserDetailsService
import site.liangbai.liar.entity.dto.auth.UserAuthorize

interface UserAuthorizeService : IService<UserAuthorize>, UserDetailsService {
}