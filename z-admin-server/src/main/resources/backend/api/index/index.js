// 加载用户菜单列表
const queryAuthor = () => {
  return $axios({
    url: '/system/user/author',
    method: 'post',
  })
}
