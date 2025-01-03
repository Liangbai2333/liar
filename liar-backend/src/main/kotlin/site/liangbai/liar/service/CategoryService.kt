package site.liangbai.liar.service

import com.baomidou.mybatisplus.extension.service.IService
import site.liangbai.liar.entity.dto.article.Category

interface CategoryService : IService<Category> {
    fun getCategoryByName(name: String): Category?
}