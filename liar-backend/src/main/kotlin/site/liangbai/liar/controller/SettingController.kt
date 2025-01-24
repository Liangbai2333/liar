package site.liangbai.liar.controller

import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import site.liangbai.liar.entity.Result
import site.liangbai.liar.entity.Result.Companion.successResult
import site.liangbai.liar.service.SettingService

@RestController
@RequestMapping("api/setting")
class SettingController {
    @Resource
    lateinit var settingService: SettingService

    @GetMapping("title")
    fun getTitle() = successResult(settingService.getTitle())

    @GetMapping("headline")
    fun getHeadline() = successResult(settingService.getHeadline())

    @GetMapping("summary")
    fun getSummary() = successResult(settingService.getSummary())

    @GetMapping("footer")
    fun getFooter() = successResult(settingService.getFooter())

    @GetMapping("set")
    fun setSetting(
        @RequestParam(required = false) title: String? = null,
        @RequestParam(required = false) headline: String? = null,
        @RequestParam(required = false) summary: String? = null,
        @RequestParam(required = false) footer: String? = null
    ): Result<Unit> {
        title?.let { settingService.setTitle(it) }
        headline?.let { settingService.setHeadline(it) }
        summary?.let { settingService.setSummary(it) }
        footer?.let { settingService.setFooter(it) }
        return successResult()
    }
}