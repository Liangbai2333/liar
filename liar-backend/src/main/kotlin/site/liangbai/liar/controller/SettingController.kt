package site.liangbai.liar.controller

import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import site.liangbai.liar.entity.Result
import site.liangbai.liar.service.system.SettingService

@RestController
@RequestMapping("api/setting")
class SettingController {
    @Resource
    lateinit var settingService: SettingService

    @GetMapping("info")
    fun getInfo() = Result.success(settingService.getSettingVO())
}