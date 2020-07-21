import request from '@/utils/request'

// 查询部署管理列表
export function listDeploy(query) {
  return request({
    url: '/maintenance/deploy/list',
    method: 'get',
    params: query
  })
}

// 查询部署管理详细
export function getDeploy(deployId) {
  return request({
    url: '/maintenance/deploy/' + deployId,
    method: 'get'
  })
}

// 新增部署管理
export function addDeploy(data) {
  return request({
    url: '/maintenance/deploy',
    method: 'post',
    data: data
  })
}

// 修改部署管理
export function updateDeploy(data) {
  return request({
    url: '/maintenance/deploy',
    method: 'put',
    data: data
  })
}

// 删除部署管理
export function delDeploy(deployId) {
  return request({
    url: '/maintenance/deploy/' + deployId,
    method: 'delete'
  })
}

// 导出部署管理
export function exportDeploy(query) {
  return request({
    url: '/maintenance/deploy/export',
    method: 'get',
    params: query
  })
}

export function queryApp(query) {
  return request({
    url: '/maintenance/deploy/queryApp',
    method: 'get',
    params: query
  })
}

export function queryServer(query) {
  return request({
    url: '/maintenance/deploy/queryServer',
    method: 'get',
    params: query
  })
}
export function serverStatusquery(query) {
  return request({
    url: '/maintenance/deploy/serverStatus/' + query,
    method: 'get'
  })
}
export function serverStart(query) {
  return request({
    url: '/maintenance/deploy/startServer/' + query,
    method: 'get',
    timeout:5000000
  })
}
export function serverStop(query) {
  return request({
    url: '/maintenance/deploy/stopServer/' + query,
    method: 'get',
    timeout:5000000
  })
}


