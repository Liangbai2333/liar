package site.liangbai.liar.service

import com.baomidou.mybatisplus.extension.service.IService
import site.liangbai.liar.entity.dto.Setting

interface SettingService : IService<Setting> {
    fun getTitle(): String

    fun setTitle(title: String)

    fun getHeadline(): String

    fun setHeadline(headline: String)

    fun getSummary(): String

    fun setSummary(summary: String)

    fun getFooter(): String

    fun setFooter(footer: String)
}