package com.example.demo.mapper;

import com.example.demo.pojo.RetireStatsVo;
import com.example.demo.pojo.employee;
import com.example.demo.pojo.retire;
import com.example.demo.pojo.transfer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    @Select("select  e.id,b.card_no as bankCard,name, gender, age, phone, email, department_id, position_name, hire_date, base_salary, status,avatar, e.create_time from employee e left join bank_card  b on  e.id =b.employee_id;")
    public List<employee> findAll() ;
//根据ID删除部门
    @Delete("delete from employee where id=#{id}")
    void deleteById(Integer id);
//新增员工
@Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into employee(name,gender,age,phone,email,department_id,position_name,hire_date,base_salary,status,avater,create_time) values(#{name},#{gender},#{age},#{phone},#{email},#{departmentId},#{positionName},#{hireDate},#{baseSalary},#{status},#{avatar},#{createTime})")
    void insert(employee employee);
//根据ID查询员工数据
    @Select("select  id,name,gender,age,phone,email,department_id,position_name,hire_date,base_salary,status,avatar from employee where id=#{id}")
    employee getById(Integer id);
//    修改员工数据
    @Update("update employee set name=#{name},gender=#{gender},age=#{age},phone=#{phone},email=#{email},department_id=#{departmentId},position_name=#{positionName},hire_date=#{hireDate},base_salary=#{baseSalary},status=#{status},avatar=#{avatar} where id=#{id};")
    void update(employee employee);
//    增加调度信息
    @Insert("insert into employee_transfer(employee_id,old_departmentId,new_departmentId,old_positionName,new_positionName,reason,transfer_date,status,create_time,update_time) values(#{employeeId},#{oldDepartmentId},#{newDepartmentId},#{oldPositionName},#{newPositionName},#{reason},#{transferDate},#{status},#{createTime},#{updateTime})")
    void transferInsert(transfer transfer);
@Select("select  id,employee_id,old_departmentId,new_departmentId,old_positionName,new_positionName,reason,transfer_date,status,create_time,update_time from employee_transfer ;")
   public List<transfer> transferList();
//审核通过
//    修改员工职位
@Select("select employee_id from employee_transfer where id= #{id}")
Integer getEmployeeId(Integer id);
@Select("select new_positionName from employee_transfer where id= #{id}")
String getNewPositionName(Integer id);
@Update("update employee set position_name=#{newPositionName} where id=#{id} ")
 void updatePosition(Integer id,String newPositionName);
    @Update("update employee_transfer set status='已通过',update_time=NOW()   where id=#{id};")
    Integer transferapprove(Integer id);
//    审核驳回
    @Update("update employee_transfer set status='已驳回',update_time=NOW()   where id=#{id};")
    void reject(Integer id);
@Select("select  id,employee_id,name,retire_type,department_id,retire_date,remark,create_time from employee_retire ;")
    List<retire> retireList();
@Select("select COUNT(CASE WHEN retire_type = '退休' AND YEAR(retire_date) = YEAR(CURDATE()) AND retire_date <= CURDATE() THEN 1 END) as retireCount,\n" +
        "    COUNT(CASE WHEN retire_type = '离职' AND YEAR(retire_date) = YEAR(CURDATE()) AND retire_date <= CURDATE() THEN 1 END) as leaveCount,\n" +
        "    COUNT(CASE WHEN retire_date > CURDATE() THEN 1 END) as pendingCount\n" +
        "FROM employee_retire")
    List<RetireStatsVo> getRetireStats();
/*删除员工离职信息*/
    @Delete("delete from employee_retire where employee_id= #{id}")
    void deleteRetire(Integer id);
    /*新增员工退休离职记录*/
@Insert("insert into employee_retire(employee_id,name,retire_type,department_id,retire_date,remark,create_time) values(#{employeeId},#{name},#{retireType},#{departmentId},#{retireDate},#{remark},#{createTime})")
    void addRetire(retire retire);
/*更新员工状态*/
    @Update("update employee set status=#{retireType} where id= #{employeeId}")
    void updateRetire(retire retire);
@Select("select  id,username,real_name from sys_user where username=#{username} and password=#{password};")
    employee SelectByNameAndPassword(employee employee);
//分页查询员工
@Select("select count(*) from employee  where #{Id} is null or id = #{Id}")
    Long count(Integer employeeId);
@Select("select e.* ,b.card_no bankCard from employee e left join bank_card b on e.id=b.employee_id order by e.create_time "+
        "limit #{start}, #{pageSize}")
    List<employee> list(Integer start, Integer pageSize, Integer id);
//添加银行卡号

@Insert("insert into bank_card(employee_id,card_no) values(#{id},#{bankCard})")
    void insertcardNo(employee employee);
//修改银行卡号
@Update("update bank_card set card_no=#{bankCard} where employee_id=#{id}")
    void updatetcardNo(employee employee);
}
