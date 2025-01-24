package site.liangbai.liar.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import site.liangbai.liar.entity.dto.Setting
import site.liangbai.liar.mapper.SettingMapper
import site.liangbai.liar.service.SettingService

@Service
class SettingServiceImpl : ServiceImpl<SettingMapper, Setting>(), SettingService {
    fun getSetting(): Setting {
        return getById(1) ?: Setting().also { save(it) }
    }

    override fun getTitle(): String {
        return getSetting().title!!
    }

    override fun setTitle(title: String) {
        updateById(getSetting().apply { this.title = title })
    }

    override fun getHeadline(): String {
        return getSetting().headline!!
    }

    override fun setHeadline(headline: String) {
        updateById(getSetting().apply { this.headline = headline })
    }

    override fun getSummary(): String {
        return getSetting().summary!!
    }

    override fun setSummary(summary: String) {
        updateById(getSetting().apply { this.summary = summary })
    }

    override fun getFooter(): String {
        return getSetting().footer!!
    }

    override fun setFooter(footer: String) {
        updateById(getSetting().apply { this.footer = footer })
    }
}