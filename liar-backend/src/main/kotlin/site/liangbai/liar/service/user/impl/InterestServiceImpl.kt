package site.liangbai.liar.service.user.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import site.liangbai.liar.entity.dto.user.Interest
import site.liangbai.liar.mapper.user.InterestMapper
import site.liangbai.liar.service.user.InterestService

@Service
class InterestServiceImpl : ServiceImpl<InterestMapper, Interest>(), InterestService {
    override fun getInterestListByUserId(userId: Int): List<Interest> {
        return query().eq("user_id", userId).list()
    }
}