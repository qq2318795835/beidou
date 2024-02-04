import request from '@/utils/request'

// 查询仓库区域列表
export function listWareHouseArea(query) {
  return request({
    url: '/devData/wareHouseArea/list',
    method: 'get',
    params: query
  })
}

// 查询仓库区域详细
export function getWareHouseArea(id) {
  return request({
    url: '/devData/wareHouseArea/' + id,
    method: 'get'
  })
}

// 新增仓库区域
export function addWareHouseArea(data) {
  return request({
    url: '/devData/wareHouseArea',
    method: 'post',
    data: data
  })
}

// 修改仓库区域
export function updateWareHouseArea(data) {
  return request({
    url: '/devData/wareHouseArea',
    method: 'put',
    data: data
  })
}

// 删除仓库区域
export function delWareHouseArea(id) {
  return request({
    url: '/devData/wareHouseArea/' + id,
    method: 'delete'
  })
}
