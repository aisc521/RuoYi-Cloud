import request from '@/utils/request'

// 查询数据库管理列表
export function listDatabase(query) {
  return request({
    url: '/maintenance/database/list',
    method: 'get',
    params: query
  })
}

// 查询数据库管理详细
export function getDatabase(dbId) {
  return request({
    url: '/maintenance/database/' + dbId,
    method: 'get'
  })
}

// 新增数据库管理
export function addDatabase(data) {
  return request({
    url: '/maintenance/database',
    method: 'post',
    data: data
  })
}

// 修改数据库管理
export function updateDatabase(data) {
  return request({
    url: '/maintenance/database',
    method: 'put',
    data: data
  })
}

// 删除数据库管理
export function delDatabase(dbId) {
  console.log("dbID:" + dbId)
  return request({
    url: '/maintenance/database/' + dbId,
    method: 'delete'
  })
}

// 导出数据库管理
export function exportDatabase(query) {
  return request({
    url: '/maintenance/database/export',
    method: 'get',
    params: query
  })
}

export function testDbConnect(data) {
  return request({
    url: '/maintenance/database/testConnect',
    method: 'post',
    data
  })
}
