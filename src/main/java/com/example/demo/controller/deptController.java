package com.example.demo.controller;

import com.example.demo.pojo.*;
import com.example.demo.service.DeptService;
import com.example.demo.service.EmployService;
import com.example.demo.service.SalaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.awt.SystemColor.info;

//部门Controller
@Slf4j
@RestController
public class deptController {
    @Autowired
    private DeptService deptService;
    /*查询学院部门axios.get('/college/list', { params: { name: searchName.value || undefined } })*/
    @GetMapping("/college/list")
    public Result list(@RequestParam(required = false) String departmentName){
        log.info("查询学院部门:{}", departmentName);
        List<dept> deptList;
        if(departmentName != null && !departmentName.isEmpty()) {
            deptList = deptService.findByNameContaining(departmentName);
        } else {
            deptList = deptService.findAll();
        }
        return Result.success(deptList);
    }
//新增学院部门
    @PostMapping("/college/add")
    public Result add(@RequestBody dept dept){
        log.info("新增学院部门:{}",dept);
        deptService.add(dept);
        return Result.success();
    }
//    编辑学院部门
    @PutMapping("/college/update")
    public Result update(@RequestBody dept dept) {
        deptService.update(dept);
        return Result.success();
    }
//    删除学院部门
    @DeleteMapping("/college/delete/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除学院部门:{}",id);
        deptService.delete(id);
        return Result.success();
    }
//查询行政部门
    @GetMapping("/dept/list")
    public Result listDepartment(){
        log.info("查询行政部门");
        List<dept> deptList=deptService.XfindAll();
        return Result.success(deptList);
    }
//    计算行政部门人数
    @GetMapping("/dept/totalStaff")
    public Result totalStaff(){
        log.info("计算行政部门人数");
        int totalStaff=deptService.totalStaff();
        return Result.success(totalStaff);
    }
//    删除 部门
    @DeleteMapping("/dept/delete/{id}")
    public Result Xdelete(@PathVariable Integer id) {
        log.info("删除部门:{}", id);
        deptService.Xdelete(id);
        return Result.success();
    }
//    新增
    @PostMapping("/dept/add")
    public Result Xadd(@RequestBody dept dept) {
        log.info("新增部门:{}", dept);
        deptService.Xadd(dept);
        return Result.success();
    }
    /*修改*/
    @PutMapping("/dept/update")
    public Result Xupdate(@RequestBody dept dept) {
        log.info("修改部门:{}", dept);
        deptService.Xupdate(dept);
        return Result.success();
    }

}
