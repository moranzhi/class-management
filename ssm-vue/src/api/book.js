
    import request from '@/utils/request'

export default {
  getPageList(params) {
    return request({
      url: '/ssm/books',
      method: 'get',
      params
    })
  },

  addBook(data) {
    return request({
      url: '/ssm/book',
      method: 'post',
      data
    })
  },
  updateBook(data) {
    return request({
      url: '/ssm/book',
      method: 'put',
      data
    })
  },
  getDelById(id) {
    return request({
      url: `/ssm/book/del?id=${id}`,
      method: 'get'
    })
  }
}


    