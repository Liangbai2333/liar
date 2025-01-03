package site.liangbai.liar.entity.vo.response.article

import site.liangbai.liar.entity.dto.article.Tag
import site.liangbai.liar.entity.vo.VOTransformer

data class TagVO(
    val id: Int,
    val name: String
) {
    companion object : VOTransformer<Tag, TagVO> {
        override fun internalTransform(entity: Tag): TagVO {
            return TagVO(
                id = entity.id!!,
                name = entity.name!!
            )
        }
    }
}