// 查询列表接口
function getSettingList (params) {
    return $axios({
      url: '/system/setting/page',
      method: 'get',
      params
    })
  }
  
// 删除接口
const deleteSetting = (ids) => {
  return $axios({
    url: '/system/setting/delete',
    method: 'post',
    params: { ids }
  })
}

// 新增接口
const addSetting = (params) => {
  return $axios({
    url: '/system/setting/save',
    method: 'post',
    data: { ...params }
  })
}

// 根据id查询单条数据，用于修改页面数据回显
const querySettingById = (id) => {
  return $axios({
    url: `/system/setting/findById`,
    method: 'get',
    params: { id }
  })
}
  
// 修改接口
const editSetting = (params) => {
  return $axios({
    url: '/system/setting/edit',
    method: 'put',
    data: { ...params }
  })
}