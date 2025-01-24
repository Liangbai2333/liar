package site.liangbai.liar.service

import com.baomidou.mybatisplus.extension.service.IService
import site.liangbai.liar.entity.dto.stat.Setting
import site.liangbai.liar.entity.vo.response.stat.SettingVO

interface SettingService : IService<Setting> {
    fun getSettingVO(): SettingVO

    fun saveSetting(
        title: String,
        headline: String,
        summary: String,
        footer: String
    ): Boolean

    fun getTitle(): String

    fun setTitle(title: String)

    fun getHeadline(): String

    fun setHeadline(headline: String)

    fun getSummary(): String

    fun setSummary(summary: String)

    fun getFooter(): String

    fun setFooter(footer: String)
}