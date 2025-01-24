package site.liangbai.liar.entity.vo.response.user

import site.liangbai.liar.entity.dto.user.Contact
import site.liangbai.liar.entity.vo.VOTransformer

data class ContactVO(
    val id: Int,
    val type: String,
    val icon: String,
    val name: String,
    val value: String,
    val link: String,
) {
    companion object : VOTransformer<Contact, ContactVO> {
        override fun internalTransform(entity: Contact): ContactVO {
            return ContactVO(
                id = entity.id!!,
                type = entity.type!!,
                icon = entity.icon!!,
                name = entity.name!!,
                value = entity.value!!,
                link = entity.link!!
            )
        }
    }
}