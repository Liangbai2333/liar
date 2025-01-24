package site.liangbai.liar.service.user.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import site.liangbai.liar.entity.dto.user.Contact
import site.liangbai.liar.mapper.user.ContactMapper
import site.liangbai.liar.service.user.ContactService

@Service
class ContactServiceImpl : ServiceImpl<ContactMapper, Contact>(), ContactService {
    override fun getContactListByUserId(userId: Int): List<Contact> {
        return query().eq("user_id", userId).list()
    }
}