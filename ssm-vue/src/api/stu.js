import request from '@/utils/request'

export default {
  getPageList(params) {
    return request({
      url: '/ssm/students',
      method: 'get',
      params
    })
  },

  addProduct(data) {
    return request({
      url: '/ssm/student',
      method: 'post',
      data
    })
  },
  updateProduct(data) {
    return request({
      url: '/ssm/student',
      method: 'put',
      data
    })
  },
  getDelById(id) {
    return request({
      url: `/ssm/student/del?id=${id}`,
      method: 'get'
    })
  }
}

