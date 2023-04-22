package com.employee.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.employee.system.Param.SalaryEditParam;
import com.employee.system.Param.SearchParam;
import com.employee.system.entity.Department;
import com.employee.system.entity.Employee;
import com.employee.system.entity.Salary;
import com.employee.system.mapper.EmployeeMapper;
import com.employee.system.mapper.SalaryMapper;
import com.employee.system.service.SalaryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bluesky
 * @create 2023-04-20-22:25
 */
@Slf4j
@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryMapper salaryMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 分页查询工资信息
     *
     * @param searchParam
     * @return
     */
    @Override
    public IPage<Salary> list(SearchParam searchParam) {

        IPage<Salary> page = new Page<>(searchParam.getPageNum(), searchParam.getPageSize());
        QueryWrapper<Salary> queryWrapper = new QueryWrapper<>();

        if (searchParam.getName().trim() != null || !searchParam.getName().isEmpty()) {
            queryWrapper.like("employee_name", searchParam.getName().trim());
        }
        IPage<Salary> salaryIPage = salaryMapper.selectPage(page, queryWrapper);

        log.info("DepartmentServiceImpl.list业务结束，结果: {}", "部门列表获取成功！");
        return salaryIPage;

    }

    /**
     * 编辑部门
     *
     * @param salaryEditParam
     * @return
     */
    @Override
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int edit(SalaryEditParam salaryEditParam) {

        // 1.修改工资表
        QueryWrapper<Salary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", salaryEditParam.getId());

        Salary one = salaryMapper.selectOne(queryWrapper);

        one.setSalary(salaryEditParam.getSalary());
        one.setBasicSalary(salaryEditParam.getBasicSalary());
        one.setBonus(salaryEditParam.getBonus());
        one.setSubsidy(salaryEditParam.getSubsidy());
        one.setOvertimeWages(salaryEditParam.getOvertimeWages());
        one.setDockWages(salaryEditParam.getDockWages());

        salaryMapper.updateById(one);

        // 2.修改员工表salary
        QueryWrapper<Employee> employeeQueryWrapper = new QueryWrapper<>();
        employeeQueryWrapper.eq("id", one.getEmployeeId());
        Employee employee = employeeMapper.selectOne(employeeQueryWrapper);
        employee.setSalary(one.getSalary());
        int result = employeeMapper.updateById(employee);

        log.info("SalaryServiceImpl.edit业务结束，结果: {}", "工资修改成功！");
        return result;
    }

    /**
     * 回显
     *
     * @param id
     * @return
     */
    @Override
    public Salary back(Long id) {

        QueryWrapper<Salary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Salary salary = salaryMapper.selectOne(queryWrapper);

        log.info("SalaryServiceImpl.back业务结束，结果: {}","回显工资信息成功！");
        return salary;
    }
}
