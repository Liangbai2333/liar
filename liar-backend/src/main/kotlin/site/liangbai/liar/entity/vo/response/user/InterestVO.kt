package site.liangbai.liar.entity.vo.response.user

import site.liangbai.liar.entity.dto.user.Interest

data class InterestVO(
    val id: Int,
    val icon: String,
    val name: String,
) {
    companion object {
        fun internalTransform(entity: Interest): InterestVO {
            return InterestVO(
                id = entity.id!!,
                icon = entity.icon!!,
                name = entity.name!!,
            )
        }
    }
}