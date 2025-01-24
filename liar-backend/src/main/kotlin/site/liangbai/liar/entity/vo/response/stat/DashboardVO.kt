package site.liangbai.liar.entity.vo.response.stat

data class DashboardVO(
    val os: String,
    val arch: String,
    val cpuUsedPercent: Double,
    val memoryUsed: Double,
    val memoryTotal: Double,
    val memoryUsedPercent: Double,
    val javaVersion: String,
    val runningTime: Long,
    val startTime: Long,
    val serverTime: Long
)