package site.liangbai.liar.service.user

import com.baomidou.mybatisplus.extension.service.IService
import site.liangbai.liar.entity.dto.user.Skill

interface SkillService : IService<Skill> {
    fun getSkullListByUserId(userId: Int): List<Skill>
}