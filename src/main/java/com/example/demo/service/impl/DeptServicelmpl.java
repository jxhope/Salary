package com.example.demo.service.impl;

import com.example.demo.mapper.DeptMapper;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.pojo.dept;
import com.example.demo.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class  DeptServicelmpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
//查询学院部门
    @Override
    public List<dept> findAll() {
        return deptMapper.findAll();
    }
    //   新增学院
    @Override
    public void add(dept dept) {
//       增加更新时间
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }
    //编辑修改学院信息
    @Override
    public void update(dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
//   根据学院名称查询学院
    @Override
    public List<dept> findByNameContaining(String departmentName) {
        return deptMapper.findByNameContaining(departmentName);
    }
//删除学院部门
    @Override
    public void delete(Integer id) {
        deptMapper.delete(id);
    }
//查询行政列表
    @Override
    public List<dept> XfindAll() {
        return deptMapper.XfindAll();
    }
//    查询行政人数
    @Override
    public int totalStaff() {
        return deptMapper.totalStaff();
    }
    @Override
    public void Xdelete(Integer id) {
        deptMapper.Xdelete(id);
    }

    @Override
    public void Xadd(dept dept) {

        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.Xadd(dept);
    }

    @Override
    public void Xupdate(dept dept) {

        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.Xupdate(dept);
    }
    /*修改行政部门*/



}
