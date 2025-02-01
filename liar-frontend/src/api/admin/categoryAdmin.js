export function saveCategory(id, params) {
    return request({
        url: `/admin/category/save/${id}`,
        method: 'post',
        params: params
    })
}

export function deleteCategory(id) {
    return request({
        url: `/admin/category/delete/${id}`,
        method: 'delete'
    })
}

export function uploadIcon(file) {
    return request({
        url: `/admin/category/upload/icon`,
        method: 'post',
        params: {
            file: file
        }
    })
}

