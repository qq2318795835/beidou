import request from '@/utils/request'

// 查询企业信息列表
export function listCompany(query) {
  return request({
    url: '/devDate/company/list',
    method: 'get',
    params: query
  })
}

// 查询企业信息详细
export function getCompany(id) {
  return request({
    url: '/devDate/company/' + id,
    method: 'get'
  })
}

// 新增企业信息
export function addCompany(data) {
  return request({
    url: '/devDate/company',
    method: 'post',
    data: data
  })
}

// 修改企业信息
export function updateCompany(data) {
  return request({
    url: '/devDate/company',
    method: 'put',
    data: data
  })
}

// 删除企业信息
export function delCompany(id) {
  return request({
    url: '/devDate/company/' + id,
    method: 'delete'
  })
}
