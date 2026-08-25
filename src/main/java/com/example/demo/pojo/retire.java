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
public class retire {
    private Integer id;
    private String name;
    private Integer employeeId;
    private String retireType;
    private Integer departmentId;
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date retireDate;
    private String remark;
    private LocalDateTime createTime;

}
