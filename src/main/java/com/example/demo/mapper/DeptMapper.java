package com.example.demo.mapper;

import com.example.demo.pojo.dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
//查询学院列表
public interface DeptMapper {
@Select("select id, department_name, department_type, leader_name, phone, create_time from department where department_type='学院'")
    List<dept> findAll();
//新增学院
@Insert("insert into department(id,department_name, department_type, leader_name, phone, create_time,update_time) values(#{id},#{departmentName},#{departmentType},#{leaderName},#{phone},#{createTime},#{updateTime})")
    void add(dept dept);
//修改学院部门信息
@Update("update department set department_name=#{departmentName}, leader_name=#{leaderName}, phone=#{phone}, update_time=#{updateTime} where id=#{id}")
    void update(dept dept);
//模糊查询学院名称
    @Select("select id, department_name, department_type, leader_name, phone, create_time from department where department_name like '%${value}%'and department_type='学院'")
    List<dept> findByNameContaining(String departmentName);
@Delete("delete from department where id=#{id}")
    void delete(Integer id);
@Select("select id, department_name, department_type, leader_name, phone, create_time from department where department_type='行政部门'")
    List<dept> XfindAll();
//查询行政部门人数
@Select("select count(*) from employee e right join department d on e.department_id=d.id where department_type='行政部门'")
    int totalStaff();
//删除行政部门
    @Delete("delete from department where id= #{id}")
    void Xdelete(Integer id);
//    新增部门
@Insert("insert into department(id,department_name, department_type, leader_name, phone, create_time,update_time) values(#{id},#{departmentName},#{departmentType},#{leaderName},#{phone},#{createTime},#{updateTime})")
    void Xadd(dept dept);
@Update("update department set department_name= #{departmentName}, leader_name= #{leaderName}, phone= #{phone}, update_time= #{updateTime} where id= #{id}")
    void Xupdate(dept dept);
}
