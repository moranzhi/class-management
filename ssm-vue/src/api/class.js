
    import request from '@/utils/request'

export default {
  getPageList(params) {
    return request({
      url: '/ssm/classs',
      method: 'get',
      params
    })
  },

  addClass(data) {
    return request({
      url: '/ssm/class',
      method: 'post',
      data
    })
  },
  updateClass(data) {
    return request({
      url: '/ssm/class',
      method: 'put',
      data
    })
  },
  getDelById(id) {
    return request({
      url: `/ssm/class/del?id=${id}`,
      method: 'get'
    })
  }
}


    