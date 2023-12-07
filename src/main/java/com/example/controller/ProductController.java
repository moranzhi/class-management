package com.example.controller;

import com.github.pagehelper.PageInfo;
import com.example.domain.Product;
import com.example.entity.Result;
import com.example.entity.ResultCode;
import com.example.service.IProductService;
import com.example.utils.DateUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author example
 * @version V1.0
 * @Package com.example.controller
 * @date 2020/3/23 下午9:56
 * @Copyright ©
 */
@RestController
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public Result findAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer limit) {
        List<Product> list = productService.findAll(page, limit);
        PageInfo pageInfo = new PageInfo(list);
        return new Result(ResultCode.SUCCESS, pageInfo);
    }

    @PostMapping("/product")
    public Result save(@RequestBody Product product) {
        productService.save(product);
        return new Result(ResultCode.SUCCESS);
    }

    @GetMapping("/product/{id}")
    public Result findById(@PathVariable("id") String id){
        Product p = productService.findById(id);
        return new Result(ResultCode.SUCCESS,p);
    }


    @GetMapping("/product/del")
    public Result delete(@RequestParam String id){
        productService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }

    @PutMapping("/product")
    public Result update(@RequestBody Product product){
        productService.update(product);
        return new Result(ResultCode.SUCCESS);
    }

}
