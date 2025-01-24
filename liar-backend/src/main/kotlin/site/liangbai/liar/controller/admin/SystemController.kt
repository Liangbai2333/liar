package site.liangbai.liar.controller.admin

import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.*
import site.liangbai.liar.entity.Result
import site.liangbai.liar.service.system.SettingService

@RestController
@RequestMapping("api/admin/system")
class SystemController {
    @Resource
    lateinit var settingService: SettingService

    @GetMapping("setting/info")
    fun getSetting() = Result.success(settingService.getSettingVO())

    @PostMapping("setting/save")
    fun saveSetting(
        @RequestParam title: String,
        @RequestParam headline: String,
        @RequestParam summary: String,
        @RequestParam footer: String
    ) = Result.success(settingService.saveSetting(title, headline, summary, footer))
}