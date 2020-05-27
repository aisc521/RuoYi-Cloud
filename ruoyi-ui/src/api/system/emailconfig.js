import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listConfig(query) {
  return request({
    url: '/system/email/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getConfig(id) {
  return request({
    url: '/system/email/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addConfig(data) {
  return request({
    url: '/system/email',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateConfig(data) {
  return request({
    url: '/system/email',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delConfig(id) {
  return request({
    url: '/system/email/' + id,
    method: 'delete'
  })
}

// 导出【请填写功能名称】
export function exportConfig(query) {
  return request({
    url: '/system/email/export',
    method: 'get',
    params: query
  })
}
