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
public class employee {
    private Integer id;
    private String name;
    private String username;
    private String realName;
    private String password;
    private String gender;
    private Integer age;
    private String phone;
    private String email;
    private int departmentId;
    private String bankCard;
    private String positionName;
    private String avatar;
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date hireDate;
    private float baseSalary;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
