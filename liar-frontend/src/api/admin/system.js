export function getSystemInfo() {
    return request({
        url: '/admin/system/setting/info',
        method: 'get'
    })
}

export function saveSystemInfo(params) {
    return request({
        url: '/admin/system/setting/save',
        method: 'post',
        params: params
    })
}
