package site.liangbai.liar

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@MapperScan("site.liangbai.liar.mapper")
class LiarBackendApplication

fun main(args: Array<String>) {
    runApplication<LiarBackendApplication>(*args)
}
