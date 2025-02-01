package site.liangbai.liar

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootTest
class LiarBackendApplicationTests {

    @Test
    fun contextLoads() {
        val passwordEncoder = BCryptPasswordEncoder()
        val rawPassword = "123456"
        val encodedPassword = passwordEncoder.encode(rawPassword)
        println("Encoded password: $encodedPassword")
    }

}
