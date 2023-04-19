package com.employee.system.service;

import com.employee.system.Param.DepartmentEditParam;
import com.employee.system.Param.PageParam;
import com.employee.system.entity.Department;

import java.util.List;

/**
 * @author bluesky
 * @create 2023-04-19-21:42
 */
public interface DepartmentService {

    /**
     * 分页查询部门列表
     * @param pageParam
     * @return
     */
    List<Department> list(PageParam pageParam);

    /**
     * 新增部门
     * @param department
     * @return
     */
    int add(Department department);

    /**
     * 编辑部门
     * @param departmentEditParam
     * @return
     */
    int edit(DepartmentEditParam departmentEditParam);

    /**
     * 删除部门
     * @param id
     * @return
     */
    int delete(Long id);
}
