package com.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.example.dao.IProductDao;
import com.example.domain.Product;
import com.example.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author example
 * @version V1.0
 * @Package com.example.service.impl
 * @date 2020/3/23 下午9:58
 * @Copyright ©
 */
@Service("productService")
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;
    @Override
    public List<Product> findAll(Integer page,Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        product.setId(UUID.randomUUID().toString().replace("-",""));
        productDao.save(product);
    }

    @Override
    public Product findById(String id) {
        return productDao.findById(id);
    }

    @Override
    public void update(Product p) {
        Product product = productDao.findById(p.getId());
        product.setProductNum(p.getProductNum());
        product.setProductName(p.getProductName());
        product.setCityName(p.getCityName());
        product.setDepartureTime(p.getDepartureTime());
        product.setProductPrice(p.getProductPrice());
        product.setProductDesc(p.getProductDesc());
        product.setProductStatus(p.getProductStatus());
        productDao.update(product);
    }

    @Override
    public void delete(String id) {
        productDao.delete(id);
    }
}
