package com.example.demo.pojo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;

@Data

public class bonus {
    private Integer id;
    private Integer employeeId;
    private String name;
    private Date bonusMonth;
    private String bonusType;
    private float  money;
    private String remark;

    private Date createTime;
}
