package com.employee.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.employee.system.Param.PageParam;
import com.employee.system.entity.Employee;
import com.employee.system.mapper.EmployeeMapper;
import com.employee.system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bluesky
 * @create 2023-04-17-23:26
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 分页查询员工列表
     * @param pageParam
     * @return
     */
    @Override
    public List<Employee> list(PageParam pageParam) {

        IPage<Employee> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        List<Employee> employeeList = employeeMapper.selectPage(page, queryWrapper).getRecords();

        return employeeList;
    }

    /**
     * 新增员工
     * @param employee
     * @return
     */
    @Override
    public int add(Employee employee) {

        int result = employeeMapper.insert(employee);

        return result;
    }

    /**
     * 编辑员工
     * @param employee
     * @return
     */
    @Override
    public int edit(Employee employee) {

        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",employee.getId());

        Employee one = employeeMapper.selectOne(queryWrapper);

        one.setEmployeeName(employee.getEmployeeName());
        one.setDepartmentId(employee.getDepartmentId());
        one.setAge(employee.getAge());

        int result = employeeMapper.updateById(one);

        return result;
    }

    /**
     * 删除员工
     * @param id
     * @return
     */
    @Override
    public int delete(Long id) {

        int result = employeeMapper.deleteById(id);

        return result;
    }
}
