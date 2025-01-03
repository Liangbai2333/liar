package site.liangbai.liar.entity.vo

interface VOTransformer<E, V> {
    fun fromEntity(entity: E?): V? {
        return entity?.let { internalTransform(it) }
    }

    fun internalTransform(entity: E): V
}