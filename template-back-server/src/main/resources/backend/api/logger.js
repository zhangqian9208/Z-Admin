// 获取数据的请求
function getLoggerList (params) {
    return $axios({
      url: '/system/logger/page',
      method: 'get',
      params
    })
  }
  
  // 修改---启用禁用接口
  function enableOrDisableEmployee (params) {
    return $axios({
      url: '/employee',
      method: 'put',
      data: { ...params }
    })
  }
  