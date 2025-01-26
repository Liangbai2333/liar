export function getSettings() {
    return request({
        url: '/setting/info',
        method: 'get'
    })
}
