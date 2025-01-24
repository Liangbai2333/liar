package site.liangbai.liar.service.user

import com.baomidou.mybatisplus.extension.service.IService
import site.liangbai.liar.entity.dto.user.SkillCategory

interface SkillCategoryService : IService<SkillCategory> {
    fun getSkullCategories(): List<SkillCategory>
}