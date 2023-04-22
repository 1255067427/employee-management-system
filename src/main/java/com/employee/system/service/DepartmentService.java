package com.employee.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.employee.system.Param.DepartmentEditParam;
import com.employee.system.Param.PageParam;
import com.employee.system.Param.SearchParam;
import com.employee.system.entity.Department;
import com.employee.system.entity.Employee;

import java.util.List;

/**
 * @author bluesky
 * @create 2023-04-19-21:42
 */
public interface DepartmentService {

    /**
     * 分页查询部门列表
     *
     * @param searchParam
     * @return
     */
    IPage<Department> list(SearchParam searchParam);

    /**
     * 新增部门
     *
     * @param department
     * @return
     */
    int add(Department department);

    /**
     * 编辑部门
     *
     * @param departmentEditParam
     * @return
     */
    int edit(DepartmentEditParam departmentEditParam);

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 获取所有部门
     *
     * @return
     */
    List<Department> depList();

    /**
     * 回显
     *
     * @param id
     * @return
     */
    Department back(Long id);
}
