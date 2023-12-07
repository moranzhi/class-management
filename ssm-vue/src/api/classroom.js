
    import request from '@/utils/request'

export default {
  getPageList(params) {
    return request({
      url: '/ssm/classrooms',
      method: 'get',
      params
    })
  },

  addClassroom(data) {
    return request({
      url: '/ssm/classroom',
      method: 'post',
      data
    })
  },
  updateClassroom(data) {
    return request({
      url: '/ssm/classroom',
      method: 'put',
      data
    })
  },
  getDelById(id) {
    return request({
      url: `/ssm/classroom/del?id=${id}`,
      method: 'get'
    })
  }
}


    