package site.liangbai.liar.service.system

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
}