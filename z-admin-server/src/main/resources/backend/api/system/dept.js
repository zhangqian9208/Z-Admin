// 查询列表接口
function getDeptList(params) {
  return $axios({
    url: '/system/dept/page',
    method: 'get',
    params
  })
}

// 删除接口
const deleteDept = (ids) => {
  return $axios({
    url: '/system/dept/delete',
    method: 'post',
    params: { ids }
  })
}

// 新增接口
const addDept = (params) => {
  return $axios({
    url: '/system/dept/save',
    method: 'post',
    data: { ...params }
  })
}

// 根据id查询单条数据，用于修改页面数据回显
const queryDeptById = (id) => {
  return $axios({
    url: `/system/dept/findById`,
    method: 'post',
    params: { id }
  })
}

// 修改接口
const editDept = (params) => {
  return $axios({
    url: '/system/dept/edit',
    method: 'put',
    data: { ...params }
  })
}


