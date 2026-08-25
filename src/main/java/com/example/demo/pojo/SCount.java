package com.example.demo.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SCount {
    /*paidSalary: 0,
  pendingCount: 0,
  pendingAmount: 0*/
    private Integer paidSalary;
    private Integer pendingCount;
    private Integer pendingAmount;
}
