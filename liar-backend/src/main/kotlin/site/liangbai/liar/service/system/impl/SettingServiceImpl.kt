package site.liangbai.liar.service.system.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import site.liangbai.liar.entity.dto.stat.Setting
import site.liangbai.liar.entity.vo.response.stat.SettingVO
import site.liangbai.liar.entity.vo.response.stat.SettingVO.Companion.fromEntity
import site.liangbai.liar.mapper.SettingMapper
import site.liangbai.liar.service.system.SettingService

@Service
class SettingServiceImpl : ServiceImpl<SettingMapper, Setting>(), SettingService {

    fun getSetting(): Setting {
        return getById(1) ?: Setting().also { save(it) }
    }

    override fun getSettingVO(): SettingVO {
        return fromEntity(getSetting())!!
    }

    override fun saveSetting(title: String, headline: String, summary: String, footer: String): Boolean {
        return updateById(getSetting().apply {
            this.title = title
            this.headline = headline
            this.summary = summary
            this.footer = footer
        })
    }
}