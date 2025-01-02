package site.liangbai.liar.mapper.article

import jakarta.annotation.Resource
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TagMapperTest {
    @Resource
    lateinit var tagMapper: TagMapper

    @Test
    fun test() {
        println(("----- selectAll method test ------"));
        val tags = tagMapper.selectList(null)
        tags.forEach { println(it) }
    }
}