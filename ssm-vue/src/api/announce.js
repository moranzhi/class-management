
    import request from '@/utils/request'

export default {
  getPageList(params) {
    return request({
      url: '/ssm/announces',
      method: 'get',
      params
    })
  },

  addAnnounce(data) {
    return request({
      url: '/ssm/announce',
      method: 'post',
      data
    })
  },
  updateAnnounce(data) {
    return request({
      url: '/ssm/announce',
      method: 'put',
      data
    })
  },
  getDelById(id) {
    return request({
      url: `/ssm/announce/del?id=${id}`,
      method: 'get'
    })
  }
}


    