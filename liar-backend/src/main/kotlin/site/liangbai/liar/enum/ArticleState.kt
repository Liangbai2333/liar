package site.liangbai.liar.enum

import com.baomidou.mybatisplus.annotation.EnumValue

enum class ArticleState(@EnumValue private val value: Int) {
    DRAFT(0),
    PUBLISHED(1),
    DELETED(2),
    PRIVATE(3);

    companion object {
        fun fromValue(value: Int): ArticleState {
            return entries.first { it.value == value }
        }
    }
}