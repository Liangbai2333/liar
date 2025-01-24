package site.liangbai.liar.service

import jakarta.annotation.Resource
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import site.liangbai.liar.service.system.StatService
import site.liangbai.liar.service.user.UserService

@SpringBootTest
class StatServiceTest {
    @Resource
    lateinit var statService: StatService
    @Resource
    lateinit var userService: UserService

    @Test
    fun test() {
        val dashboard = statService.getDashboard()
        println(dashboard)
        val users = userService.getUserList()
        println(users)
    }
}