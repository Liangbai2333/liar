export function getUserList() {
    return request({
        url: '/user/list',
        method: 'get'
    })
}
