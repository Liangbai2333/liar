package site.liangbai.liar.service.user.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import site.liangbai.liar.entity.dto.user.SkillCategory
import site.liangbai.liar.mapper.user.SkillCategoryMapper
import site.liangbai.liar.service.user.SkillCategoryService

@Service
class SkillCategoryServiceImpl : ServiceImpl<SkillCategoryMapper, SkillCategory>(), SkillCategoryService {
    override fun getSkullCategories(): List<SkillCategory> {
        return list()
    }
}