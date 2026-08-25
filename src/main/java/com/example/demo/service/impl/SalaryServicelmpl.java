package com.example.demo.service.impl;
import com.example.demo.mapper.SalaryMapper;
import com.example.demo.pojo.*;
import com.example.demo.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.time.YearMonth;
import java.util.*;

@Service
public class SalaryServicelmpl implements SalaryService {
    @Autowired
    private SalaryMapper salaryMapper;

    @Override
    public PageResult<salary> Spage(Integer page, Integer pageSize, Integer employeeId) {
            // 1. 查询总记录数
            Long total = salaryMapper.Scount();
            // 2. 计算分页起始位置
            Integer start = (page - 1) * pageSize;
            // 3. 查询结果列表
            List<salary> records = salaryMapper.Slist(start, pageSize);
            // 4. 返回前端期望的 PageResult
            return new PageResult<salary>(total, records);
        }

    @Override
    public List<salary> findAll() {
        return salaryMapper.findAll();
    }

    @Override
    public void add(salary salary) {
        salary.setCreateTime(LocalDateTime.now());
        salaryMapper.add(salary);
    }

    @Override
    public List<bonus> bonusList() {
        return salaryMapper.bonusList();
    }

    @Override
    public List<deduction> deductionList() {
        return salaryMapper.deductionList();
    }

    @Override
    public void addDeduction(deduction deduction) {
        deduction.setCreateTime(LocalDateTime.now());
        salaryMapper.addDeduction(deduction);
    }
//修改扣款信息
    @Override
    public void updateBonus(bonus bonus) {
        salaryMapper.updateBonus(bonus);
    }

    @Override
    public void updateDeduction(deduction deduction) {
        salaryMapper.updateDeduction(deduction);
    }

    @Override
    public void addBonus(bonus bonus) {
        salaryMapper.addBonus(bonus);
    }

    @Override
    public void deleteBonus(Integer id) {
        salaryMapper.deleteBonus(id);
    }

    @Override
    public void deleteDeduction(Integer id) {
        salaryMapper.deleteDeduction(id);
    }

    @Override
    public PageResult<salary> page(Integer page, Integer pageSize, Integer employeeId) {
        // 1. 查询总记录数，支持可选 employeeId
        Long total = salaryMapper.count(employeeId);
        // 2. 计算分页起始位置
        Integer start = (page - 1) * pageSize;
        // 3. 查询结果列表
        List<salary> rows = salaryMapper.list(start, pageSize, employeeId);
        // 4. 返回前端期望的 PageResult
        return new PageResult<salary>(total, rows);
    }
//    /查询银行卡号
    @Override
    public List<card> getCardNo(Integer employeeId) {
        return salaryMapper.getCardNo(employeeId);
    }
    //修改发放状态
    @Override
    public void updateStatus(Integer id) {
        salaryMapper.updateStatus(id);
    }

    @Override
    public void Cardadd(card card) {
        card.setCreateTime(LocalDateTime.now());
        salaryMapper.Cardadd(card);
    }
//统计发送数据
    public Map<String, Object> getStatistics() {
        Long paid = salaryMapper.countPaid();
        Long unpaid = salaryMapper.countUnpaid();
        Double total = salaryMapper.totalSalary();
        Map<String, Object> stats = new HashMap<>();
        stats.put("paidCount", paid != null ? paid : 0);
        stats.put("unpaidCount", unpaid != null ? unpaid : 0);
        stats.put("totalSalary", total != null ? total : 0.0);
        return stats;
    }
//统计本月
    @Override
    public summary getSalarySummary() {
        summary result = salaryMapper.getTotalSummary();
        return result;
    }

    @Override
    public Map<String, Object>  getMonthlyData() {
        Map<String, Object> map = new HashMap<>();

        map.put("month", salaryMapper.getMonthList());
        map.put("salary", salaryMapper.getMonthlySalary());
        map.put("bonus", salaryMapper.getMonthlyBonus());
        map.put("deduction", salaryMapper.getMonthlyDeduction());
        return map;
    }
    @Override
    public List<Map<String, Object>> getReportList() {
        return salaryMapper.getDepartmentReport();
    }
    //获取本月工资明细
    @Override
    public List<salary>getCurrentMonthSalary() {
        return salaryMapper.getCurrentMonthSalary();
    }

    @Override
    public List<SCount> getMonthSalary() {
        return salaryMapper.getMonthSalary();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> batchLastMonthSalary() {
        // 1. 获取上个月
        String salaryMonth = YearMonth.now().toString();
        // 2. 查询全部员工
        List<Map<String, Object>> employees = salaryMapper.getAllEmployees();
        if (employees == null || employees.isEmpty()) {
            throw new RuntimeException("没有查询到员工信息");
        }
        // 3. 查询已经录入工资的员
        List<Long> existsEmployeeIds = salaryMapper.getExistingEmployeeIds(salaryMonth);
        Set<Long> existsSet = new HashSet<>(existsEmployeeIds == null ? Collections.emptyList() : existsEmployeeIds);
        // 4. 查询上月奖金
        List<Map<String, Object>> bonusList = salaryMapper.getMonthBonus(salaryMonth);
        Map<Long, BigDecimal> bonusMap = new HashMap<>();
        for (Map<String, Object> item : bonusList) {
            Long employeeId = toLong(item.get("employeeId"));
            BigDecimal money = toBigDecimal(item.get("money"));
            bonusMap.put(employeeId, money);
        }
        // 5. 查询上月扣款
        List<Map<String, Object>> deductionList = salaryMapper.getMonthDeduction(salaryMonth);
        Map<Long, BigDecimal> deductionMap = new HashMap<>();
        for (Map<String, Object> item : deductionList) {
            Long employeeId = toLong(item.get("employeeId"));
            BigDecimal money = toBigDecimal(item.get("money"));
            deductionMap.put(employeeId, money);
        }
        // 6. 构造工资数据
        List<Map<String, Object>> salaryList = new ArrayList<>();
        int skipCount = 0;
        for (Map<String, Object> employee : employees) {
            Long employeeId = toLong(employee.get("id"));
            if (employeeId == null) {
                continue;
            }
            // 已经录入过
            if (existsSet.contains(employeeId)) {
                skipCount++;
                continue;
            }

            BigDecimal baseSalary = toBigDecimal(employee.get("baseSalary"));
            BigDecimal bonus = bonusMap.getOrDefault(employeeId, BigDecimal.ZERO);
            BigDecimal deduction = deductionMap.getOrDefault(employeeId, BigDecimal.ZERO);
            // 实发工资
            BigDecimal realSalary = baseSalary.add(bonus).subtract(deduction);
            Map<String, Object> salary = new HashMap<>();
            salary.put("employeeId", employeeId);
            salary.put("name", employee.get("name"));
            salary.put("salaryMonth", salaryMonth);
            // 考勤，默认30天
            salary.put("attendanceDays", 30);
            salary.put("baseSalary", baseSalary);
            salary.put("bonus", bonus);
            salary.put("deduction", deduction);
            salary.put("tax", BigDecimal.ZERO);
            salary.put("realSalary", realSalary);
            salary.put("salaryStatus", "待发放");
            salaryList.add(salary);
        }
        // 7. 批量插入
        int successCount = 0;
        if (!salaryList.isEmpty()) {
            successCount = salaryMapper.batchInsertSalary(salaryList);
        }
        // 8. 返回批量处理结果
        Map<String, Object> result = new HashMap<>();
        result.put("salaryMonth", salaryMonth);
        result.put("successCount", successCount);
        result.put("skipCount", skipCount);
        result.put("failCount", 0);
        result.put("message", salaryMonth +
                        "工资批量录入完成"
        );

        return result;
    }

    /**
     * Object转Long
     */
    private Long toLong(Object value) {

        if (value == null) {
            return null;
        }

        if (value instanceof Number) {
            return ((Number) value).longValue();
        }

        return Long.parseLong(
                String.valueOf(value)
        );
    }

    /**
     * Object转BigDecimal
     */
    private BigDecimal toBigDecimal(Object value) {

        if (value == null) {
            return BigDecimal.ZERO;
        }

        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }

        return new BigDecimal(
                String.valueOf(value)
        );
    }

}
