package site.liangbai.liar.util.delegate

import org.springframework.context.ApplicationContext
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.javaType

class ObjectBeanField<T>(private val name: String? = null) {
    private var value: T? = null

    @Suppress("UNCHECKED_CAST")
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: run {
            val bean = if (name != null) context.getBean(name, property.returnType.javaType as Class<*>) else context.getBean(property.returnType.javaType as Class<*>)
            value = bean as T
            bean
        }
    }

    companion object {
        lateinit var context: ApplicationContext
    }
}

fun <T> bean(): ObjectBeanField<T> = ObjectBeanField()

fun <T> bean(name: String): ObjectBeanField<T> = ObjectBeanField(name)