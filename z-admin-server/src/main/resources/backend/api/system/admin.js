// 查询列表接口
function getAdminList (params) {
    return $axios({
      url: '/system/user/page',
      method: 'get',
      params
    })
  }
  
// 删除接口
const deleteAdmin = (ids) => {
  return $axios({
    url: '/system/user/delete',
    method: 'post',
    params: { ids }
  })
}

// 新增接口
const addAdmin = (params) => {
  return $axios({
    url: '/system/user/save',
    method: 'post',
    data: { ...params }
  })
}

// 根据id查询单条数据，用于修改页面数据回显
const queryAdminById = (id) => {
  return $axios({
    url: `/system/user/findById`,
    method: 'get',
    params: { id }
  })
}
  
// 修改接口
const editAdmin = (params) => {
  return $axios({
    url: '/system/user/edit',
    method: 'put',
    data: { ...params }
  })
}

// 获取部门分类列表
const getDeptList = (params) => {
  return $axios({
    url: '/system/dept/list',
    method: 'get',
    params
  })
}

//保存用户角色列表
const updateRole = (roles,adminId) => {
  return $axios({
    url: '/system/user/updateRole',
    method: 'post',
    data: {roles,adminId}
  })
}

// 根据用户id查询对应角色，用于授权页面数据回显
const queryRoleById = (id) => {
  return $axios({
    url: `/system/user/userRoleList`,
    method: 'get',
    params: { id }
  })

// 
}