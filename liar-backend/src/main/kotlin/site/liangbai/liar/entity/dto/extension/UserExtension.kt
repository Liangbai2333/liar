package site.liangbai.liar.entity.dto.extension

import site.liangbai.liar.entity.dto.user.Contact
import site.liangbai.liar.entity.dto.user.Interest
import site.liangbai.liar.entity.dto.user.Skill
import site.liangbai.liar.entity.dto.user.User
import site.liangbai.liar.service.user.ContactService
import site.liangbai.liar.service.user.InterestService
import site.liangbai.liar.service.user.SkillService
import site.liangbai.liar.util.delegate.bean

object UserExtension : BaseEntityExtension<User>() {
    private val skillService by bean<SkillService>()
    private val contactService by bean<ContactService>()
    private val interestService by bean<InterestService>()

    val User.skills: List<Skill>
        get() = getOrPutValue("skills") {
            skillService.getSkullListByUserId(id!!)
        }

    val User.contacts: List<Contact>
        get() = getOrPutValue("contacts") {
            contactService.getContactListByUserId(id!!)
        }

    val User.interests: List<Interest>
        get() = getOrPutValue("interests") {
            interestService.getInterestListByUserId(id!!)
        }
}