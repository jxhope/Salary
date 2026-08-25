package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*分页结果封装类*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T>{
    private Long total;//总记录数
    private List<T> rows;

}
