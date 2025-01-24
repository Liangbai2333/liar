package site.liangbai.liar.service.user

import com.baomidou.mybatisplus.extension.service.IService
import site.liangbai.liar.entity.dto.user.Contact

interface ContactService : IService<Contact> {
    fun getContactListByUserId(userId: Int): List<Contact>
}