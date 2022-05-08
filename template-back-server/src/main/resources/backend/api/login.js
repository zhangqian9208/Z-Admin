function loginApi(data) {
  return $axios({
    url: '/system/user/login',
    method: 'post',
    data
  })
}

function logoutApi() {
  return $axios({
    url: '/system/user/logout',
    method: 'post',
  })
}
