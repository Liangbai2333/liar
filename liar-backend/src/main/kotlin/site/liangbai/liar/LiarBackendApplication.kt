package site.liangbai.liar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LiarBackendApplication

fun main(args: Array<String>) {
    runApplication<LiarBackendApplication>(*args)
}
