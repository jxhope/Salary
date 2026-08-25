package com.example.demo.mapper;

import com.example.demo.pojo.*;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SalaryMapper {
    /**
     * 统计当月工资总记录
     */
    Long Scount();
    /**
     * 分页查询当月工资记录
     */
    List<salary> Slist(
            @Param("start") Integer start,
            @Param("pageSize") Integer pageSize
    );
    /**
     * 查询全部工资记录
     */
    List<salary> findAll();
    /**
     * 增加工资
     */
    void add(salary salary);
    /**
     * 查询奖金
     */
    List<bonus> bonusList();
    /**
     * 查询扣款
     */
    List<deduction> deductionList();
    /**
     * 增加扣款
     */
    void addDeduction(deduction deduction);
    /**
     * 修改奖金
     */
    void updateBonus(bonus bonus);
    /**
     * 修改扣款
     */
    void updateDeduction(deduction deduction);
    /**
     * 新增奖金
     */
    void addBonus(bonus bonus);
    /**
     * 删除奖金
     */
    void deleteBonus(Integer id);
    /**
     * 删除扣款
     */
    void deleteDeduction(Integer id);
    /**
     * 工资记录总数
     */
    Long count(@Param("employeeId") Integer employeeId);
    /**
     * 分页查询工资
     */
    List<salary> list(
            @Param("start") Integer start,
            @Param("pageSize") Integer pageSize,
            @Param("employeeId") Integer employeeId
    );
    /**
     * 查询员工银行卡
     */
    List<card> getCardNo(
            @Param("employeeId") Integer employeeId
    );
    /**
     * 修改工资发放状态
     */
    void updateStatus(
            @Param("id") Integer id
    );
    /**
     * 新增银行卡
     */
    void Cardadd(card card);
    /**
     * 已发放工资数量
     */
    Long countPaid();
    /**
     * 待发放工资数量
     */
    Long countUnpaid();
    /**
     * 发放总金额
     */
    Double totalSalary();
    /**
     * 本月工资统计
     */
    summary getTotalSummary();
    /**
     * 获取工资月份列表
     */
    List<String> getMonthList();
    /**
     * 获取每月基础工资
     */
    List<Double> getMonthlySalary();
    /**
     * 获取每月奖金
     */
    List<Double> getMonthlyBonus();
    /**
     * 获取每月扣款
     */
    List<Double> getMonthlyDeduction();
    /**
     * 部门工资报表
     */
    List<Map<String, Object>> getDepartmentReport();
    /**
     * 查询当月工资
     */
    List<salary> getCurrentMonthSalary();
    /**
     * 获取当月工资统计
     */
    List<SCount> getMonthSalary();
    /**
     * 查询工资录入记录
     */
    List<Map<String, Object>> getSalaryList();
    /**
     * 查询指定员工指定月份是否已经录入
     */
    Integer checkSalaryExists(
            @Param("employeeId") Object employeeId,
            @Param("salaryMonth") Object salaryMonth
    );
    /**
     * 手动插入工资
     */
    int insertSalary(
            Map<String, Object> salary
    );
    /**
     * 查询全部员工
     */
    List<Map<String, Object>> getAllEmployees();
    /**
     * 查询指定月份已经录入工资的员工
     */
    List<Long> getExistingEmployeeIds(
            @Param("salaryMonth") String salaryMonth
    );

    /**
     * 查询指定月份奖金
     */
    List<Map<String, Object>> getMonthBonus(
            @Param("salaryMonth") String salaryMonth
    );

    /**
     * 查询指定月份扣款
     */
    List<Map<String, Object>> getMonthDeduction(
            @Param("salaryMonth") String salaryMonth
    );

    int batchInsertSalary(List<Map<String, Object>> salaryList);
}