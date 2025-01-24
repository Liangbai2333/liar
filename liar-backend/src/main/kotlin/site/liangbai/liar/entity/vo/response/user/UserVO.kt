package site.liangbai.liar.entity.vo.response.user

import site.liangbai.liar.entity.dto.extension.UserExtension.contacts
import site.liangbai.liar.entity.dto.extension.UserExtension.interests
import site.liangbai.liar.entity.dto.extension.UserExtension.skills
import site.liangbai.liar.entity.dto.user.User
import site.liangbai.liar.entity.vo.VOTransformer

data class UserVO(
    val id: Int,
    val username: String,
    val avatar: String,
    val motto: String,
    val description: String,
    val status: String,
    val skills: List<SkillVO>,
    val interests: List<InterestVO>,
    val contacts: List<ContactVO>
) {
    companion object : VOTransformer<User, UserVO> {
        override fun internalTransform(entity: User): UserVO {
            return UserVO(
                id = entity.id!!,
                username = entity.username!!,
                avatar = entity.avatar!!,
                motto = entity.motto!!,
                description = entity.description!!,
                status = entity.status!!,
                skills = entity.skills.map { SkillVO.internalTransform(it) },
                interests = entity.interests.map { InterestVO.internalTransform(it) },
                contacts = entity.contacts.map { ContactVO.internalTransform(it) }
            )
        }
    }
}