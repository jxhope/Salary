package com.example.demo.service;

import com.example.demo.pojo.dept;

import java.util.List;

public interface DeptService {
    List<dept> findAll();

    void add(dept dept);

    void update(dept dept);

    List<dept> findByNameContaining(String departmentName);

    void delete(Integer id);

    List<dept> XfindAll();

    int totalStaff();
    void Xdelete(Integer id);

    void Xadd(dept dept);

    void Xupdate(dept dept);
}
