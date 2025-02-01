import request from './request'

/**
 * 管理员登录
 * @param {Object} data
 * @param {string} data.username 用户名
 * @param {string} data.password 密码
 */
export function login(data) {
  return request({
    url: '/admin/user/login',
    method: 'post',
    data,
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}

/**
 * 退出登录
 */
export function logout(data) {
  return request({
    url: '/admin/user/logout',
    method: 'post',
    data,
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
} 