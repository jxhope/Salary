package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class salary {
    private Integer id;
    private Integer employeeId;
    private String Name;
    private String salaryMonth;
    private Integer attendanceDays;
    private String department;
    private Double baseSalary;
    private String cardNo;
    private Double bonus;
    private Double deduction;
    private Double tax;
    private Double realSalary;
    private String salaryStatus;
    private LocalDateTime createTime;
}
