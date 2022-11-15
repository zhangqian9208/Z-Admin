// 查询全部接口
function getModuleListAll (params) {
  return $axios({
    url: '/system/module/list',
    method: 'get',
    params
  })
}

// 查询列表接口
function getModuleList (params) {
    return $axios({
      url: '/system/module/page',
      method: 'get',
      params
    })
  }
  
// 删除接口
const deleteModule = (ids) => {
  return $axios({
    url: '/system/module/delete',
    method: 'post',
    params: { ids }
  })
}

// 新增接口
const addModule = (params) => {
  return $axios({
    url: '/system/module/save',
    method: 'post',
    data: { ...params }
  })
}

// 根据id查询单条数据，用于修改页面数据回显
const queryModuleById = (id) => {
  return $axios({
    url: `/system/module/findById`,
    method: 'get',
    params: { id }
  })
}
  
// 修改接口
const editModule = (params) => {
  return $axios({
    url: '/system/module/edit',
    method: 'put',
    data: { ...params }
  })
}