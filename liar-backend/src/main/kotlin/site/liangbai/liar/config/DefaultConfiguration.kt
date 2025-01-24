package site.liangbai.liar.config

import jakarta.annotation.PostConstruct
import jakarta.annotation.Resource
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Configuration
import site.liangbai.liar.util.delegate.ObjectBeanField

@Configuration
class DefaultConfiguration {
    @Resource
    lateinit var context: ApplicationContext

    @PostConstruct
    fun init() {
        ObjectBeanField.context = context
    }
}