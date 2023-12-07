package com.example.service;

import com.example.domain.Product;

import java.util.List;

/**
 * @author example
 * @version V1.0
 * @Package com.example.service
 * @date 2020/3/23 下午9:58
 * @Copyright ©
 */
public interface IProductService {
    List<Product> findAll(Integer page,Integer pageSize);
    void save(Product product);

    Product findById(String id);

    void update(Product product);

    void delete(String id);
}
