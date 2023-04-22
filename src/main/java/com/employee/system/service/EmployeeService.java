package com.employee.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.employee.system.Param.EmployeeEditParam;
import com.employee.system.vo.EmployeeListVo;
import com.employee.system.Param.SearchParam;
import com.employee.system.entity.Employee;

import java.util.List;

/**
 * @author bluesky
 * @create 2023-04-17-23:26
 */
public interface EmployeeService {

    /**
     * 分页查询员工列表
     * @param searchParam
     * @return
     */
    List<EmployeeListVo> list(SearchParam searchParam);

    /**
     * 新增员工
     * @param employee
     * @return
     */
    int add(Employee employee);

    /**
     * 编辑员工
     * @param employeeEditParam
     * @return
     */
    int edit(EmployeeEditParam employeeEditParam);

    /**
     * 删除员工
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 回显
     * @param id
     * @return
     */
    Employee back(Long id);
}
