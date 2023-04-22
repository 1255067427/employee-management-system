package com.employee.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.employee.system.Param.SalaryEditParam;
import com.employee.system.Param.SearchParam;
import com.employee.system.entity.Department;
import com.employee.system.entity.Salary;

/**
 * @author bluesky
 * @create 2023-04-20-22:25
 */
public interface SalaryService {

    /**
     * 分页查询工资信息
     * @param searchParam
     * @return
     */
    IPage<Salary> list(SearchParam searchParam);

    /**
     * 编辑部门
     * @param salaryEditParam
     * @return
     */
    int edit(SalaryEditParam salaryEditParam);

    /**
     * 回显
     *
     * @param id
     * @return
     */
    Salary back(Long id);
}
