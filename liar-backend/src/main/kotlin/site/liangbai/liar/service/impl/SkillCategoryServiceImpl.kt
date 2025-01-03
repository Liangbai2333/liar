package site.liangbai.liar.service.impl

import com.baomidou.mybatisplus.extension.service.IService
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import site.liangbai.liar.entity.dto.user.SkillCategory
import site.liangbai.liar.mapper.user.SkillCategoryMapper
import site.liangbai.liar.service.SkillCategoryService

class SkillCategoryServiceImpl : ServiceImpl<SkillCategoryMapper, SkillCategory>(), SkillCategoryService {
}