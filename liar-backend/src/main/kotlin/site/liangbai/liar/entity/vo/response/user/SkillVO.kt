package site.liangbai.liar.entity.vo.response.user

import site.liangbai.liar.entity.dto.extension.SkillExtension.skillCategory
import site.liangbai.liar.entity.dto.user.Skill
import site.liangbai.liar.entity.vo.VOTransformer

data class SkillVO(
    val id: Int,
    val category: SkillCategoryVO,
    val name: String,
    val level: Int,
) {
    companion object : VOTransformer<Skill, SkillVO> {
        override fun internalTransform(entity: Skill): SkillVO {
            return SkillVO(
                id = entity.id!!,
                category = SkillCategoryVO.fromEntity(entity.skillCategory)!!,
                name = entity.name!!,
                level = entity.level!!
            )
        }
    }
}