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
public class transfer {
    private Integer id;
    private Integer employeeId;
    private Integer oldDepartmentId;
    private Integer newDepartmentId;
    private String newPositionName;
    private String oldPositionName;
    private String reason;
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date transferDate;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
