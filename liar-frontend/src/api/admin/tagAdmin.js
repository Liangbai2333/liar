export function saveTag(id, params) {
    return request({
        url: `/admin/tag/save/${id}`,
        method: 'post',
        params: params
    })
}

export function deleteTag(id) {
    return request({
        url: `/admin/tag/delete/${id}`,
        method: 'delete'
    })
}
