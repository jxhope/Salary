package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

//员工信息
@Data
@NoArgsConstructor
@AllArgsConstructor
public class card {
    /*employeeId: '',

  employeeName: '',

  bankCardNo: '',

  bankName: ''*/
    private Integer employeeId;
    private String employeeName;
    private String bankCardNo;
    private String bankName;
    private LocalDateTime createTime;
}
