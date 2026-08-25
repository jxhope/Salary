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
public class RetireStatsVo {
    private Integer retireCount;   // 本年退休人数
    private Integer leaveCount;    // 本年离职人数
    private Integer pendingCount;  // 待办理人数
}
