package site.liangbai.liar.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import site.liangbai.liar.entity.dto.article.Category
import site.liangbai.liar.entity.vo.response.article.CategoryVO
import site.liangbai.liar.mapper.article.CategoryMapper
import site.liangbai.liar.service.CategoryService
import site.liangbai.liar.util.ifNotEmpty

@Service
class CategoryServiceImpl : ServiceImpl<CategoryMapper, Category>(), CategoryService {
    override fun getCategoryByName(name: String): Category? {
        return lambdaQuery().eq(Category::name, name).one()
    }

    override fun getCategoryList(): List<CategoryVO> {
        return lambdaQuery().list()?.map { CategoryVO.internalTransform(it) } ?: emptyList()
    }

    override fun saveCategory(id: Int, name: String, description: String?, icon: String?, color: String?): Boolean {
        val category = if (id == 0) {
            Category().apply {
                this.name = name
                this.description = description
                this.icon = icon
                this.color = color
            }
        } else {
            getById(id)
        } ?: return false
        name.ifNotEmpty {
            category.name = name
        }
        description?.let {
            category.description = it
        }
        icon?.let {
            category.icon = it
        }
        color?.let {
            category.color = it
        }
        return saveOrUpdate(category)
    }

    override fun deleteCategory(id: Int): Boolean {
        return removeById(id)
    }
}