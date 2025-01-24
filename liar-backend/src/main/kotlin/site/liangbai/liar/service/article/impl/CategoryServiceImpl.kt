package site.liangbai.liar.service.article.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import site.liangbai.liar.entity.dto.article.Category
import site.liangbai.liar.entity.vo.response.article.CategoryVO
import site.liangbai.liar.mapper.article.CategoryMapper
import site.liangbai.liar.service.article.CategoryService
import site.liangbai.liar.service.system.FileUploadService
import site.liangbai.liar.util.ifNotEmpty
import java.nio.file.Files
import java.nio.file.Paths

@Service
class CategoryServiceImpl(
    private val fileUploadService: FileUploadService
) : ServiceImpl<CategoryMapper, Category>(), CategoryService {
    private val iconDir = Paths.get("uploads/category/icon")

    init {
        if (!Files.exists(iconDir)) {
            Files.createDirectories(iconDir)
        }
    }

    override fun getCategoryByName(name: String): Category? {
        return query().eq("name", name).one()
    }

    override fun getCategoryList(): List<CategoryVO> {
        return query().list()?.map { CategoryVO.internalTransform(it) } ?: emptyList()
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

    override fun uploadIconImage(file: MultipartFile): String {
        return fileUploadService.uploadImage(file, iconDir)
    }
}