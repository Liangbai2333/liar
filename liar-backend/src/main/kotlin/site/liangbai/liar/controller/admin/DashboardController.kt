package site.liangbai.liar.controller.admin

import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import site.liangbai.liar.entity.Result
import site.liangbai.liar.service.ArticleService
import site.liangbai.liar.service.StatService

@RestController
@RequestMapping("api/admin/dashboard")
class DashboardController {
    @Resource
    lateinit var statService: StatService
    @Resource
    lateinit var articleService: ArticleService

    @GetMapping("info")
    fun info() = Result.success(statService.getDashboard())

    @GetMapping("total_view")
    fun totalView() = Result.success(articleService.list().sumOf { it.views!! })
}