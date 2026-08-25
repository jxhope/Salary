package com.example.demo.controller;

import com.example.demo.pojo.*;
import com.example.demo.service.EmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//员工信息Controller
@RestController
public class employeeController {
    @Autowired
    private EmployService employService;
    @GetMapping("/employee/list")
    public Result list(@RequestParam Integer page,
                       @RequestParam Integer pageSize,
                       @RequestParam(required = false) Integer Id){
        PageResult<employee> pageResult =employService.page(page, pageSize,Id);
        return Result.success(Map.of(
                "records", pageResult.getRows(),
                "total", pageResult.getTotal()
        ));
    }
//    删除员工
    @DeleteMapping("/employee/delete")
    public Result delete(Integer id){
     System.out.println("删除员工信息"+id);
     employService.deleteById(id);
        return Result.success();
    }
//    新增员工
    @PostMapping("/employee/add")
    public Result add(@RequestBody employee employee){
        System.out.println("新增员工信息"+employee);
        employService.add(employee);
        return Result.success();
    }

//    根据ID查询员工
@GetMapping("/employee/{id}")
public Result getById(@PathVariable Integer id){
    System.out.println("根据ID查询员工: "+id);
  employee employee =  employService.getById(id);
    return Result.success(employee);
}
//修改员工
    @PutMapping("/employee/update")
    public Result update(@RequestBody employee employee){
        System.out.println("修改员工信息"+employee);
        employService.update(employee);
        return Result.success();
    }
//调动员工
    @PostMapping("/employee/transfer")
    public Result transfer(@RequestBody transfer transfer){
        System.out.println("调动员工信息"+transfer);
        employService.transferInsert(transfer);
        return Result.success();
    }
//    查询调度请求
    @GetMapping("/employee/transfer/list")
    public Result transferList(){
        System.out.println("查询调度请求");
        List<transfer> transferList=employService.transferList();
        return Result.success(transferList);
    }
//  调度通过
@PutMapping("/employee/transfer/approve/{id}")
public Result approve(@PathVariable Integer id){
    System.out.println("已通过：" + id);
    employService.transferapprove(id);
    return Result.success();
}
//调度驳回
@PutMapping("/employee/transfer/reject/{id}")
public Result reject(@PathVariable Integer id){
    System.out.println("已驳回：" + id);
    employService.reject(id);
    return Result.success();
}
//退休查询
    @GetMapping("/employee/retire/list")
    public Result retireList(){
        System.out.println("查询退休员工");
        List<retire> retireList=employService.retireList();
        return Result.success(retireList);
    }
    @GetMapping("/employee/retire/stats")
    public Result getRetireStats() {
        System.out.println("开始查询本年度离退休统计数据...");
        List<RetireStatsVo> stats = employService.getRetireStats();
        return Result.success(stats);
    }
//    删除员工退休离职数据
    @DeleteMapping("/employee/retire/{id}")
    public Result deleteRetire(@PathVariable Integer id){
        System.out.println("删除员工退休离职数据：" + id);
        employService.deleteRetire(id);
        return Result.success();
    }
//    新增员工退休离职数据
    @PostMapping("/employee/retire")
    public Result addRetire(@RequestBody retire retire){
        System.out.println("新增员工退休离职数据：" + retire);
        employService.addRetire(retire);
        return Result.success();
    }
//
}
