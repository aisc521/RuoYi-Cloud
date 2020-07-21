import request from '@/utils/request'

// 查询服务器管理列表
export function listServer(query) {
  return request({
    url: '/maintenance/server/list',
    method: 'get',
    params: query
  })
}

// 查询服务器管理详细
export function getServer(serverId) {
  return request({
    url: '/maintenance/server/' + serverId,
    method: 'get'
  })
}

// 新增服务器管理
export function addServer(data) {
  return request({
    url: '/maintenance/server',
    method: 'post',
    data: data
  })
}

// 修改服务器管理
export function updateServer(data) {
  return request({
    url: '/maintenance/server',
    method: 'put',
    data: data
  })
}

// 删除服务器管理
export function delServer(serverId) {
  return request({
    url: '/maintenance/server/' + serverId,
    method: 'delete'
  })
}

// 导出服务器管理
export function exportServer(query) {
  return request({
    url: '/maintenance/server/export',
    method: 'get',
    params: query
  })
}
export function testServerConnect(data) {
  return request({
    url: '/maintenance/server/testConnect',
    method: 'post',
    data
  })
}
