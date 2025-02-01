import request from '../request'

export function getDashboard() {
    return request({
        url: '/admin/dashboard/info',
        method: 'get'
    })
}

export function getTotalView() {
    return request({
        url: '/admin/dashboard/total-view',
        method: 'get'
    })
}

export function getArticleCount() {
    return request({
        url: '/article/count',
        method: 'get'
    })
}