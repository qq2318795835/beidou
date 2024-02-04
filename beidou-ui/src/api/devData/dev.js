import request from '@/utils/request'

// 查询设备管理列表
export function listDev(query) {
  return request({
    url: '/devData/dev/list',
    method: 'get',
    params: query
  })
}

// 查询设备管理详细
export function getDev(id) {
  return request({
    url: '/devData/dev/' + id,
    method: 'get'
  })
}

// 新增设备管理
export function addDev(data) {
  return request({
    url: '/devData/dev',
    method: 'post',
    data: data
  })
}

// 修改设备管理
export function updateDev(data) {
  return request({
    url: '/devData/dev',
    method: 'put',
    data: data
  })
}

// 删除设备管理
export function delDev(id) {
  return request({
    url: '/devData/dev/' + id,
    method: 'delete'
  })
}
