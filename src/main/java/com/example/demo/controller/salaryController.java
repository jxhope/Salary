package com.example.demo.controller;

import com.example.demo.pojo.*;
import com.example.demo.service.SalaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.awt.SystemColor.info;

//员工信息Controller
@Slf4j
@RestController
public class salaryController {
    //    获取工资列表
    @Autowired
    SalaryService salaryService;

    @GetMapping("/employee/salary/list")
    public Result list() {
        System.out.println("员工信息");
        List<salary> empList = salaryService.findAll();
        return Result.success(empList);
    }

    //    录入工资信息
    @PostMapping("/employee/salary")
    public Result add(@RequestBody salary salary) {
        salaryService.add(salary);
        return Result.success();
    }

    //    查询奖金信息
    @GetMapping("/employee/bonus/list")
    public Result listBonus() {
        System.out.println("奖金信息");
        List<bonus> empList = salaryService.bonusList();
        return Result.success(empList);
    }

    //    查询扣款信息
    @GetMapping("/employee/deduction/list")
    public Result listDeduction() {
        System.out.println("扣款信息");
        List<deduction> empList = salaryService.deductionList();
        return Result.success(empList);
    }

    //    新增扣款信息
    @PostMapping("/employee/deduction")
    public Result addDeduction(@RequestBody deduction deduction) {
        salaryService.addDeduction(deduction);
        return Result.success();
    }

    //    修改扣款信息
    @PutMapping("/employee/deduction")
    public Result updateDeduction(@RequestBody deduction deduction) {
        salaryService.updateDeduction(deduction);
        return Result.success();
    }

    //    新增奖金信息
    @PostMapping("/employee/bonus")
    public Result addBonus(@RequestBody bonus bonus) {
        salaryService.addBonus(bonus);
        return Result.success();
    }

    //  修改奖金信息
    @PutMapping("/employee/bonus")
    public Result updateBonus(@RequestBody bonus bonus) {
        System.out.println("修改奖金信息" + bonus);
        salaryService.updateBonus(bonus);
        return Result.success();
    }

    //    删除奖金
    @DeleteMapping("/employee/bonus/{id}")
    public Result deleteBonus(@PathVariable Integer id) {
        System.out.println("删除奖金信息" + id);
        salaryService.deleteBonus(id);
        return Result.success();
    }

    //    删除扣款
    @DeleteMapping("/employee/deduction/{id}")
    public Result deleteDeduction(@PathVariable Integer id) {
        System.out.println("删除扣款信息" + id);
        salaryService.deleteDeduction(id);
        return Result.success();
    }

    //    分页查询工资
    @GetMapping("/salary/pay/list")
    public Result page(@RequestParam Integer page,
                       @RequestParam Integer pageSize,
                       @RequestParam(required = false) Integer employeeId) {
        PageResult<salary> pageResult = salaryService.page(page, pageSize, employeeId);
        return Result.success(Map.of(
                "rows", pageResult.getRows(),
                "total", pageResult.getTotal()
        ));
    }

    /*查询银行卡号*/
    @GetMapping("/employee/card/{employeeId}")
    public Result getCardNo(@PathVariable Integer employeeId) {
        log.info("查询银行卡号：{}", employeeId);
        List<card> cardNo = salaryService.getCardNo(employeeId);
        return Result.success(cardNo);
    }

    /*修改工资状态*/
    @PutMapping("/salary/pay/status/{id}")
    public Result updateStatus(@PathVariable Integer id) {
        log.info("修改工资状态：{}", id);
        salaryService.updateStatus(id);
        return Result.success();
    }

    //    保存银行卡号
    @PostMapping("/employee/card/save")
    public Result saveCard(@RequestBody card card) {
        log.info("保存银行卡号：{}", card);
        salaryService.Cardadd(card);
        return Result.success();
    }

    //    统计数据
// 工资统计接口
    @GetMapping("/salary/pay/statistics")
    public Result getStatistics() {
        Map<String, Object> stats = salaryService.getStatistics();
        return Result.success(stats);
    }
//    发放统计接口
    @GetMapping("/salary/summary")
    public Result getSummary() {
        return Result.success(
                salaryService.getSalarySummary()
        );
    }
    //  柱状图：每月统计
    @GetMapping("/salary/monthly")
    public Result getMonthly() {
        return Result.success(salaryService.getMonthlyData());
    }
    // 表格：部门报表
    @GetMapping("/salary/report")
    public Result getReport() {
        return Result.success(salaryService.getReportList());
    }
//    获取本月工资明细
@GetMapping("/salary/currentMonth")
public Result getCurrentMonthSalary(@RequestParam Integer page,
                                    @RequestParam Integer pageSize,
                                    @RequestParam(required = false) Integer employeeId) {
    PageResult<salary> pageResult =salaryService.Spage(page, pageSize, employeeId);
    return Result.success(Map.of(
            "records", pageResult.getRows(),
            "total", pageResult.getTotal()
    ));
}
//统计本月工资数据
    @GetMapping("/salary/statistics")
    public Result getCurrentMonthStatistics() {
        List<SCount> list = salaryService.getMonthSalary();
        return Result.success(list);
    }
//批量录入工资
@PostMapping("/employee/salary/batch-current-month")
public Result batchLastMonthSalary() {
    try {
        Map<String, Object> data = salaryService.batchLastMonthSalary();

        return Result.success(data);
    } catch (Exception e) {
        e.printStackTrace();
        return Result.error("批量录入工资失败：" + e.getMessage());
    }
}

}