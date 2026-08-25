package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class dept {
    private Integer id;
    private String departmentName;
    private String departmentType;
    private String leaderName;
    private String phone;
    private LocalDate createTime;
    private LocalDateTime updateTime;
}
