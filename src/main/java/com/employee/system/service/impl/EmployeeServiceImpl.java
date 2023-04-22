package com.employee.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.employee.system.Param.EmployeeEditParam;
import com.employee.system.vo.EmployeeListVo;
import com.employee.system.Param.SearchParam;
import com.employee.system.entity.Department;
import com.employee.system.entity.Employee;
import com.employee.system.entity.Salary;
import com.employee.system.mapper.DepartmentMapper;
import com.employee.system.mapper.EmployeeMapper;
import com.employee.system.mapper.SalaryMapper;
import com.employee.system.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bluesky
 * @create 2023-04-17-23:26
 */
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private SalaryMapper salaryMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 分页查询员工列表
     *
     * @param searchParam
     * @return
     */
    @Override
    public List<EmployeeListVo> list(SearchParam searchParam) {

        IPage<Employee> page = new Page<>(searchParam.getPageNum(), searchParam.getPageSize());
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();

        if (searchParam.getName().trim() != null || !searchParam.getName().isEmpty()) {
            queryWrapper.like("employee_name", searchParam.getName().trim());
        }
        IPage<Employee> employeeIPage = employeeMapper.selectPage(page, queryWrapper);

        List<Employee> employeeList = employeeIPage.getRecords();
        List<Long> ids = new ArrayList<>();
        employeeList.forEach(employee -> {
            ids.add(employee.getDepartmentId());
        });

        List<Department> departmentList = departmentMapper.selectBatchIds(ids);
        List<EmployeeListVo> listVos = new ArrayList<>();

        for (Employee employee : employeeList) {
            EmployeeListVo employeeListVo = new EmployeeListVo();
            employeeListVo.setId(employee.getId());
            employeeListVo.setEmployeeName(employee.getEmployeeName());
            employeeListVo.setAge(employee.getAge());
            employeeListVo.setSalary(employee.getSalary());

            Department department = departmentList.stream().filter(item -> item.getId().equals(employee.getDepartmentId())).findFirst().get();
            employeeListVo.setDepartmentName(department.getDepartmentName());

            listVos.add(employeeListVo);
        }

        log.info("EmployeeServiceImpl.list业务结束，结果: {}", "员工列表获取成功！");
        return listVos;
    }

    /**
     * 新增员工
     *
     * @param employee
     * @return
     */
    @Override
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int add(Employee employee) {

        // 1.新增员工
        employeeMapper.insert(employee);
        log.info("EmployeeServiceImpl.add业务结束，结果: {}", "新增员工成功！");

        // 2.新增工资
        Salary salary = new Salary();
        salary.setEmployeeName(employee.getEmployeeName());
        salary.setEmployeeId(employee.getId());
        int result = salaryMapper.insert(salary);

        log.info("EmployeeServiceImpl.add业务结束，结果: {}", "新增工资成功！");
        return result;
    }

    /**
     * 编辑员工
     *
     * @param employeeEditParam
     * @return
     */
    @Override
    public int edit(EmployeeEditParam employeeEditParam) {

        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", employeeEditParam.getId());

        Employee one = employeeMapper.selectOne(queryWrapper);

        one.setEmployeeName(employeeEditParam.getEmployeeName());
        one.setDepartmentId(employeeEditParam.getDepartmentId());
        one.setAge(employeeEditParam.getAge());

        int result = employeeMapper.updateById(one);

        log.info("EmployeeServiceImpl.edit业务结束，结果: {}", "编辑员工成功！");
        return result;
    }

    /**
     * 删除员工
     *
     * @param id
     * @return
     */
    @Override
    public int delete(Long id) {

        employeeMapper.deleteById(id);

        QueryWrapper<Salary> salaryQueryWrapper = new QueryWrapper<>();
        salaryQueryWrapper.eq("employee_id", id);
        int result = salaryMapper.delete(salaryQueryWrapper);

        log.info("EmployeeServiceImpl.delete业务结束，结果: {}", "删除员工成功！");
        return result;
    }

    /**
     * 回显
     * @param id
     * @return
     */
    @Override
    public Employee back(Long id) {

        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Employee employee = employeeMapper.selectOne(queryWrapper);

        log.info("EmployeeServiceImpl.back业务结束，结果: {}","回显员工信息成功！");
        return employee;
    }
}
