package com.example.demo.service;

import com.example.demo.pojo.*;
import org.springframework.stereotype.Service;

import java.util.List;
public interface EmployService {

    //    查询员工数据
    List<employee> findAll();
//    根据ID删除yuang
    void deleteById(Integer id);
//    新增员工

    void add(employee employee);
//根据ID查询员工
    employee getById(Integer id);

    void update(employee employee);

    void transferInsert(transfer transfer);

    List<transfer> transferList();

    void transferapprove(Integer id);

    void reject(Integer id);

    List<retire> retireList();

    List<RetireStatsVo> getRetireStats();
//删除员工离职记录
    void deleteRetire(Integer id);
/*新增员工退休离职记录*/
    void addRetire(retire retire);

    LoginInfo login(employee employee);
//分页显示员工信息
    PageResult<employee> page(Integer page, Integer pageSize, Integer Id);
}
