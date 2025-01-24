package site.liangbai.liar.service

import site.liangbai.liar.entity.vo.response.stat.DashboardVO

interface StatService {
    fun getDashboard(): DashboardVO
}