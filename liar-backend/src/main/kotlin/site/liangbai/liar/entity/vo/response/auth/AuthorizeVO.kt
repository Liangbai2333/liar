package site.liangbai.liar.entity.vo.response.auth

import site.liangbai.liar.entity.dto.auth.UserAuthorize
import site.liangbai.liar.entity.vo.VOTransformer
import java.util.*

data class AuthorizeVO(
    val username: String,
    val role: String,
    val description: String,
    var token: String?,
    var expire: Date?
) {
    companion object : VOTransformer<UserAuthorize, AuthorizeVO> {
        override fun internalTransform(entity: UserAuthorize): AuthorizeVO {
            return AuthorizeVO(
                entity.username!!,
                entity.roles!!,
                entity.description!!,
                null,
                null
            )
        }
    }
}