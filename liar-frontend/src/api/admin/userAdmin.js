export function saveUser(id, params) {
    return request({
        url: `/admin/user/save/${id}`,
        method: 'post',
        params: params
    })
}

export function changePassword(id, params) {
    return request({
        url: `/admin/user/change-password/${id}`,
        method: 'post',
        params: params
    })
}

export function uploadAvatar(file) {
    return request({
        url: `/admin/user/upload/avatar`,
        method: 'post',
        params: {
            file: file
        }
    })
}
