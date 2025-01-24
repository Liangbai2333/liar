package site.liangbai.liar.entity.vo.response.stat

import site.liangbai.liar.entity.dto.stat.Setting
import site.liangbai.liar.entity.vo.VOTransformer

data class SettingVO(
    var title: String,
    var headline: String,
    var summary: String,
    var footer: String
) {
    companion object : VOTransformer<Setting, SettingVO> {
        override fun internalTransform(entity: Setting): SettingVO {
            return SettingVO(
                entity.title!!,
                entity.headline!!,
                entity.summary!!,
                entity.footer!!
            )
        }
    }
}