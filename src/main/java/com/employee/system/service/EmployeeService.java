package com.employee.system.service;

import com.employee.system.Param.PageParam;
import com.employee.system.entity.Employee;

import java.util.List;

/**
 * @author bluesky
 * @create 2023-04-17-23:26
 */
public interface EmployeeService {

    /**
     * 分页查询员工列表
     * @param pageParam
     * @return
     */
    List<Employee> list(PageParam pageParam);

    /**
     * 新增员工
     * @param employee
     * @return
     */
    int add(Employee employee);

    /**
     * 编辑员工
     * @param employee
     * @return
     */
    int edit(Employee employee);

    /**
     * 删除员工
     * @param id
     * @return
     */
    int delete(Long id);
}
