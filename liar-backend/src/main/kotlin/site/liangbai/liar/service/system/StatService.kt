package site.liangbai.liar.service.system

import site.liangbai.liar.entity.vo.response.stat.DashboardVO

interface StatService {
    fun getDashboard(): DashboardVO
}