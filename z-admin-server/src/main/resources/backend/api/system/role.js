// 查询列表接口
function getRoleList (params) {
    return $axios({
      url: '/system/role/page',
      method: 'get',
      params
    })
  }
  
// 删除接口
const deleteRole = (ids) => {
  return $axios({
    url: '/system/role/delete',
    method: 'post',
    params: { ids }
  })
}

// 新增接口
const addRole = (params) => {
  return $axios({
    url: '/system/role/save',
    method: 'post',
    data: { ...params }
  })
}

// 根据id查询单条数据，用于修改页面数据回显
const queryRoleById = (id) => {
  return $axios({
    url: `/system/role/findById`,
    method: 'get',
    params: { id }
  })
}

// 根据id查询单条数据，用于修改页面数据回显
const queryRoleAndModuleById = (id) => {
  return $axios({
    url: `/system/role/author`,
    method: 'get',
    params: { id }
  })
}
  
// 修改接口
const editRole = (params) => {
  return $axios({
    url: '/system/role/edit',
    method: 'put',
    data: { ...params }
  })
}

//更新权限接口
const updateRoleModule = (checkedArray, st) => {
  return $axios({
    url: '/system/role/updateRoleModule',
    method: 'put',
    data: { checkedArray, st }
  })
}
