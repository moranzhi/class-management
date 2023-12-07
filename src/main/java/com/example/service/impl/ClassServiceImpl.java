package com.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.example.dao.IClassDao;
import com.example.domain.Class;
import com.example.service.IClassService;
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
@Service("classService")
public class ClassServiceImpl implements IClassService {
    @Autowired
    private IClassDao classDao;
    @Override
    public List<Class> findAll(Integer page,Integer pageSize,String name) {
        PageHelper.startPage(page,pageSize);
        return classDao.findAll(name);
    }

    @Override
    public void save(Class p) {
        classDao.save(p);
    }

    @Override
    public Class findById(String id) {
        return classDao.findById(id);
    }

    @Override
    public void update(Class p) {
        classDao.update(p);
    }

    @Override
    public void delete(String id) {
        classDao.delete(id);
    }
}
