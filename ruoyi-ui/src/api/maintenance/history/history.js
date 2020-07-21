import request from '@/utils/request'

// 查询部署历史管理列表
export function listHistory(query) {
  return request({
    url: '/maintenance/history/list',
    method: 'get',
    params: query
  })
}

// 查询部署历史管理详细
export function getHistory(historyId) {
  return request({
    url: '/maintenance/history/' + historyId,
    method: 'get'
  })
}

// 新增部署历史管理
export function addHistory(data) {
  return request({
    url: '/maintenance/history',
    method: 'post',
    data: data
  })
}

// 修改部署历史管理
export function updateHistory(data) {
  return request({
    url: '/maintenance/history',
    method: 'put',
    data: data
  })
}

// 删除部署历史管理
export function delHistory(historyId) {
  return request({
    url: '/maintenance/history/' + historyId,
    method: 'delete'
  })
}

// 导出部署历史管理
export function exportHistory(query) {
  return request({
    url: '/maintenance/history/export',
    method: 'get',
    params: query
  })
}
