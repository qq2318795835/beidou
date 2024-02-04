import request from '@/utils/request'

// 查询指标管理列表
export function listMetrics(query) {
  return request({
    url: '/devData/metrics/list',
    method: 'get',
    params: query
  })
}

// 查询指标管理详细
export function getMetrics(id) {
  return request({
    url: '/devData/metrics/' + id,
    method: 'get'
  })
}

// 新增指标管理
export function addMetrics(data) {
  return request({
    url: '/devData/metrics',
    method: 'post',
    data: data
  })
}

// 修改指标管理
export function updateMetrics(data) {
  return request({
    url: '/devData/metrics',
    method: 'put',
    data: data
  })
}

// 删除指标管理
export function delMetrics(id) {
  return request({
    url: '/devData/metrics/' + id,
    method: 'delete'
  })
}
