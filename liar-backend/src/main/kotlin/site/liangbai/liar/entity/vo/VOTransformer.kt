package site.liangbai.liar.entity.vo

interface VOTransformer<E, V> {
    fun fromEntity(entity: E?): V? {
        return if (entity != null) internalTransform(entity) else null
    }

    fun internalTransform(entity: E): V
}