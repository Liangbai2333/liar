package site.liangbai.liar.entity.dto.extension

import java.util.*

/**
 * 抽象父类，封装 WeakHashMap 缓存
 *
 * E：实体类型（Article, User 等等）
 */
abstract class BaseEntityExtension<E> {

    // 每个实体 E 对应一个 Map<String, Any>，用来存储多个扩展字段
    private val cache = WeakHashMap<E, MutableMap<String, Any?>>()

    /**
     * 这是一个通用的获取/存储逻辑。
     * - [fieldName]：字段名（比如 "category"、"anotherField" 等）
     * - [defaultValue]：如果没缓存时，该如何获取这个字段的值
     */
    protected fun <V> E.getOrPutValue(
        fieldName: String,
        defaultValue: () -> V
    ): V {
        // 先看看这个实体有没有对应的 Map
        val fieldsMap = cache.getOrPut(this) { mutableMapOf() }
        // 再看看这个字段是不是已经在 fieldsMap 里了
        @Suppress("UNCHECKED_CAST")
        return fieldsMap.getOrPut(fieldName) {
            defaultValue()
        } as V
    }
}