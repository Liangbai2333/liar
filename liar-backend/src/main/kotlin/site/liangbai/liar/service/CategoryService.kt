package site.liangbai.liar.service

import com.baomidou.mybatisplus.extension.service.IService
import site.liangbai.liar.entity.dto.article.Category
import site.liangbai.liar.entity.vo.response.article.CategoryVO

interface CategoryService : IService<Category> {
    fun getCategoryByName(name: String): Category?

    fun getCategoryList(): List<CategoryVO>
}