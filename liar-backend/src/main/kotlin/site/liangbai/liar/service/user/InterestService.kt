package site.liangbai.liar.service.user

import com.baomidou.mybatisplus.extension.service.IService
import site.liangbai.liar.entity.dto.user.Interest

interface InterestService : IService<Interest> {
    fun getInterestListByUserId(userId: Int): List<Interest>
}