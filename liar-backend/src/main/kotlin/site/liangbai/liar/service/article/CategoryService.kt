package site.liangbai.liar.service.article

import com.baomidou.mybatisplus.extension.service.IService
import org.springframework.web.multipart.MultipartFile
import site.liangbai.liar.entity.dto.article.Category
import site.liangbai.liar.entity.vo.response.article.CategoryVO

interface CategoryService : IService<Category> {
    fun getCategoryByName(name: String): Category?

    fun getCategoryList(): List<CategoryVO>

    fun saveCategory(
        id: Int,
        name: String,
        description: String?,
        icon: String?,
        color: String?
    ): Boolean

    fun deleteCategory(id: Int): Boolean

    fun uploadIconImage(file: MultipartFile): String
}