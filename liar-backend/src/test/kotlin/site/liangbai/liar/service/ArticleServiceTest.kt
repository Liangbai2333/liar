package site.liangbai.liar.service

import jakarta.annotation.Resource
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ArticleServiceTest {
    @Resource
    lateinit var articleService: ArticleService

    @Test
    fun test() {
        val articleTest = articleService.getArticleById(1)!!
        println(articleTest)
        println(articleTest.tags)

        val page = articleService.getArticleList(1)
        println(page)
    }
}