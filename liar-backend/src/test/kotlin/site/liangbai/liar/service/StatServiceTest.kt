package site.liangbai.liar.service

import jakarta.annotation.Resource
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StatServiceTest {
    @Resource
    lateinit var statService: StatService

    @Test
    fun test() {
        val dashboard = statService.getDashboard()
        println(dashboard)
    }
}