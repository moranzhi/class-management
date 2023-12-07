
package com.example.service;

import com.example.domain.Class;

import java.util.List;

/**
 * @author example
 * @version V1.0
 * @Package com.example.service
 * @date 2020/3/23 下午9:58
 * @Copyright ©
 */
public interface IClassService {
    List<Class> findAll(Integer page,Integer pageSize,String name);
    void save(Class c);

    Class findById(String id);

    void update(Class c);

    void delete(String id);
}
