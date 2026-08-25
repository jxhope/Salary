package com.example.demo.service;

import com.example.demo.mapper.SalaryMapper;
import com.example.demo.pojo.*;
import org.apache.ibatis.annotations.Insert;

import java.util.List;
import java.util.Map;

public interface SalaryService {
   PageResult<salary> Spage(Integer page, Integer pageSize, Integer employeeId);

    //    分页显示本月工资
    List<salary> findAll();
    void add(salary salary);

    List<bonus> bonusList();

    List<deduction> deductionList();

    void addDeduction(deduction deduction);
//    修改奖金信息
    void updateBonus(bonus bonus);
//修改扣款信息
    void updateDeduction(deduction deduction);

    void addBonus(bonus bonus);

    void deleteBonus(Integer id);

    void deleteDeduction(Integer id);
    /*分页查询的*/
    PageResult<salary> page(Integer page, Integer pageSize, Integer employeeId);

    List<card> getCardNo(Integer employeeId);

    void updateStatus(Integer id);

    void Cardadd(card card);

    Map<String, Object> getStatistics();

    summary getSalarySummary();

    Object getMonthlyData();

    Object getReportList();
    List<salary> getCurrentMonthSalary();

    List<SCount> getMonthSalary();

    Map<String, Object> batchLastMonthSalary();


//    Map<String, Object> getSummary();
}
