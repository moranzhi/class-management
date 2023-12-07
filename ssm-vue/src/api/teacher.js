
    import request from '@/utils/request'

export default {
  getPageList(params) {
    return request({
      url: '/ssm/teachers',
      method: 'get',
      params
    })
  },

  addTeacher(data) {
    return request({
      url: '/ssm/teacher',
      method: 'post',
      data
    })
  },
  updateTeacher(data) {
    return request({
      url: '/ssm/teacher',
      method: 'put',
      data
    })
  },
  getDelById(id) {
    return request({
      url: `/ssm/teacher/del?id=${id}`,
      method: 'get'
    })
  }
}


    