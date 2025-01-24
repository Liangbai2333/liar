package site.liangbai.liar.service.system.impl

import jakarta.annotation.PostConstruct
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.springframework.stereotype.Service
import oshi.SystemInfo
import site.liangbai.liar.entity.vo.response.stat.DashboardVO
import site.liangbai.liar.service.system.StatService
import java.lang.management.ManagementFactory

@Service
class StatServiceImpl : StatService {
    private var cpuUsage: Double = 0.0

    @PostConstruct
    fun startBackgroundUpdate() {
        // 启动协程定期更新数据
        CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                val processor = SystemInfo().hardware.processor
                val prevTicks = processor.systemCpuLoadTicks
                delay(1000) // 协程非阻塞延迟
                cpuUsage = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100
            }
        }
    }

    override fun getDashboard(): DashboardVO {
        val systemInfo = SystemInfo()
        val hardware = systemInfo.hardware
        val memory = hardware.memory
        val memoryUsed = (memory.total - memory.available).toDouble()
        val memoryUsagePercentage = memoryUsed / memory.total * 100

        return DashboardVO(
            os = System.getProperty("os.name"),
            arch = System.getProperty("os.arch"),
            cpuUsedPercent = cpuUsage,
            memoryUsed = memoryUsed / 1024 / 1024 / 1024,
            memoryTotal = memory.total.toDouble() / 1024 / 1024 / 1024,
            memoryUsedPercent = memoryUsagePercentage,
            javaVersion = System.getProperty("java.version"),
            runningTime = ManagementFactory.getRuntimeMXBean().uptime,
            startTime = System.currentTimeMillis() - ManagementFactory.getRuntimeMXBean().uptime,
            serverTime = System.currentTimeMillis()
        )
    }
}