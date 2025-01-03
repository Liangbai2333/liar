package site.liangbai.liar.entity.vo.response.article

import site.liangbai.liar.entity.dto.article.Category
import site.liangbai.liar.entity.vo.VOTransformer

data class CategoryVO(
    val id: Int,
    val name: String,
    val description: String,
    val icon: String,
    val color: String,
) {
    
    companion object : VOTransformer<Category, CategoryVO> {
        override fun internalTransform(entity: Category): CategoryVO {
            return CategoryVO(
                entity.id!!,
                entity.name!!,
                entity.description!!,
                entity.icon!!,
                entity.color!!
            )
        }
    }
}