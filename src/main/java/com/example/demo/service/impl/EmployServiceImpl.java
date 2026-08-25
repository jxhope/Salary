package com.example.demo.service.impl;

import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.pojo.*;
import com.example.demo.service.EmployService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class EmployServiceImpl implements EmployService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<employee> findAll() {
        return  employeeMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        employeeMapper.deleteById(id);
    }
    @Override
    public void add(employee employee) {
//        补全基础属性 -createTime
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employeeMapper.insert(employee);
        employeeMapper.insertcardNo( employee);
    }

    @Override
    public employee getById(Integer id) {
        return employeeMapper.getById(id);
    }

    @Override
    public void update(employee employee) {
//            1.补全基础属性-updateTime
        employee.setUpdateTime(LocalDateTime.now());
//        2.调用Mapper接口方法更新部门
        employeeMapper.update(employee);
        employeeMapper.updatetcardNo( employee);
    }

    @Override
    public void transferInsert(transfer transfer) {
//        1.补全基础属性-updateTime createTime
        transfer.setUpdateTime(LocalDateTime.now());
        transfer.setCreateTime(LocalDateTime.now());
//        补全状态
        transfer.setStatus("待审核");
//        2.调用Mapper接口方法插入调动数据
        employeeMapper.transferInsert(transfer);

    }

    @Override
    public List<transfer> transferList() {

        return employeeMapper.transferList();
    }

    @Override
    public void transferapprove(Integer id) {
       Integer flot= employeeMapper.transferapprove(id);
       if(flot==1){
        Integer emp_id=employeeMapper.getEmployeeId(id);
           String newPositionName=employeeMapper.getNewPositionName(id);
        employeeMapper.updatePosition(emp_id,newPositionName);
    }
    }
    @Override
    public void reject(Integer id) {
    employeeMapper.reject(id);
    }

    @Override
    public List<retire> retireList() {
        return employeeMapper.retireList();
    }

    @Override
    public List<RetireStatsVo> getRetireStats() {
        // 直接调用 mapper 拿到一条流统计数据

        return employeeMapper.getRetireStats();
    }

    @Override
    public void deleteRetire(Integer id) {

        employeeMapper.deleteRetire(id);
    }

    @Override
    public void addRetire(retire retire) {
        retire.setCreateTime(LocalDateTime.now());
        employeeMapper.addRetire(retire);
        employeeMapper.updateRetire(retire);
    }

    @Override
    public LoginInfo login(employee employee) {
//        1.调用mapper接口方法，根据用户名和密码查询用户
        employee emp = employeeMapper.SelectByNameAndPassword(employee);
//        2.判断是否存在，组装登录信息
        if(emp!=null){
            log.info("登录成功：{}", emp);
            String token = UUID.randomUUID().toString();
            return new LoginInfo(emp.getId(),emp.getUsername(),emp.getRealName(),token);
        }

        return null;
    }

    @Override
    public PageResult<employee> page(Integer page, Integer pageSize, Integer Id) {
        // 1. 查询总记录数，支持可选 employeeId
        Long total = employeeMapper.count(Id);
        // 2. 计算分页起始位置
        Integer start = (page - 1) * pageSize;
        // 3. 查询结果列表
        List<employee> records = employeeMapper.list(start, pageSize, Id);
        // 4. 返回前端期望的 PageResult
        return new PageResult<employee>(total, records);
    }

}
