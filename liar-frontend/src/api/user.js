import request from './request'

export function getUserList() {
    return request({
        url: '/user/list',
        method: 'get'
    })
}
