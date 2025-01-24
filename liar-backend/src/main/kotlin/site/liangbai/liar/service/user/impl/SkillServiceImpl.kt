package site.liangbai.liar.service.user.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import site.liangbai.liar.entity.dto.user.Skill
import site.liangbai.liar.mapper.user.SkillMapper
import site.liangbai.liar.service.user.SkillService

@Service
class SkillServiceImpl : ServiceImpl<SkillMapper, Skill>(), SkillService {
    override fun getSkullListByUserId(userId: Int): List<Skill> {
        return query().eq("user_id", userId).list()
    }
}