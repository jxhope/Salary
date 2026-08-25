package com.example.demo.pojo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class deduction {
    /*

    employee_id BIGINT NOT NULL COMMENT '员工ID',

    deduction_type VARCHAR(50) COMMENT '扣款类型',

    money DECIMAL(10,2) DEFAULT 0 COMMENT '金额',
    bonus_month DATE NOT NULL COMMENT '扣款月份',
    remark VARCHAR(255) COMMENT '备注',

    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'*/
    private Integer id;
    private Integer employeeId;
    private String name;
    private String deductionType;
    private float  money;
    private String remark;
    private Date deductionMonth;
    private LocalDateTime createTime;

}
