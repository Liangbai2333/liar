package site.liangbai.liar.entity.vo.response.user

import site.liangbai.liar.entity.dto.user.SkillCategory
import site.liangbai.liar.entity.vo.VOTransformer

data class SkillCategoryVO(
    val id: Int,
    val name: String
) {
    companion object : VOTransformer<SkillCategory, SkillCategoryVO> {
        override fun internalTransform(entity: SkillCategory): SkillCategoryVO {
            return SkillCategoryVO(
                id = entity.id!!,
                name = entity.name!!
            )
        }
    }
}