package site.liangbai.liar.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import site.liangbai.liar.entity.dto.article.Category
import site.liangbai.liar.mapper.article.CategoryMapper
import site.liangbai.liar.service.CategoryService

@Service
class CategoryServiceImpl : ServiceImpl<CategoryMapper, Category>(), CategoryService {
    override fun getCategoryByName(name: String): Category? {
        return lambdaQuery().eq(Category::name, name).one()
    }
}