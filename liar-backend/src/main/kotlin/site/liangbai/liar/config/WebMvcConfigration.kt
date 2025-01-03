package site.liangbai.liar.config

import com.alibaba.fastjson2.JSONReader
import com.alibaba.fastjson2.JSONWriter
import com.alibaba.fastjson2.support.config.FastJsonConfig
import com.alibaba.fastjson2.support.spring6.http.converter.FastJsonHttpMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.nio.charset.StandardCharsets


@Configuration
@EnableWebMvc
class CustomWebMvcConfigurer : WebMvcConfigurer {
    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        val converter = FastJsonHttpMessageConverter()
        val config = FastJsonConfig()
        config.dateFormat = "yyyy-MM-dd HH:mm:ss"
        config.setReaderFeatures(JSONReader.Feature.FieldBased, JSONReader.Feature.SupportArrayToBean)
        config.setWriterFeatures(JSONWriter.Feature.WriteMapNullValue, JSONWriter.Feature.PrettyFormat, JSONWriter.Feature.WriteNulls)
        converter.fastJsonConfig = config
        converter.defaultCharset = StandardCharsets.UTF_8
        converter.supportedMediaTypes = listOf(MediaType.APPLICATION_JSON)
        converters.add(0, converter)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}