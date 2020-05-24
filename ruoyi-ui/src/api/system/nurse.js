import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listNurse(query) {
  return request({
    url: '/system/nurse/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getNurse(id) {
  return request({
    url: '/system/nurse/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addNurse(data) {
  return request({
    url: '/system/nurse',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateNurse(data) {
  return request({
    url: '/system/nurse',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delNurse(id) {
  return request({
    url: '/system/nurse/' + id,
    method: 'delete'
  })
}

// 导出【请填写功能名称】
export function exportNurse(query) {
  return request({
    url: '/system/nurse/export',
    method: 'get',
    params: query
  })
}