import request from '@/utils/request'

// 查询告警信息列表
export function listAlert(query) {
  return request({
    url: '/devData/alert/list',
    method: 'get',
    params: query
  })
}

// 查询告警信息详细
export function getAlert(id) {
  return request({
    url: '/devData/alert/' + id,
    method: 'get'
  })
}

// 新增告警信息
export function addAlert(data) {
  return request({
    url: '/devData/alert',
    method: 'post',
    data: data
  })
}

// 修改告警信息
export function updateAlert(data) {
  return request({
    url: '/devData/alert',
    method: 'put',
    data: data
  })
}

// 删除告警信息
export function delAlert(id) {
  return request({
    url: '/devData/alert/' + id,
    method: 'delete'
  })
}
