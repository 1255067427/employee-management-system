package com.employee.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.employee.system.Param.EmployeeEditParam;
import com.employee.system.Param.PageParam;
import com.employee.system.Param.SearchParam;
import com.employee.system.entity.Department;
import com.employee.system.entity.Employee;
import com.employee.system.service.EmployeeService;
import com.employee.system.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author bluesky
 * @create 2023-04-17-23:24
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 分页查询员工列表
     *
     * @param searchParam
     * @return
     */
    @PostMapping("/list")
    public R list(@RequestBody SearchParam searchParam) {

        IPage<Employee> list = employeeService.list(searchParam);

        return R.ok(list);
    }

    /**
     * 新增员工
     *
     * @param employee
     * @param bindingResult
     * @return
     */
    @PostMapping("add")
    public R add(@RequestBody Employee employee, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return R.fail("添加失败，参数错误！");
        }

        int result = employeeService.add(employee);

        return R.ok(result);
    }

    /**
     * 编辑员工
     *
     * @param employeeEditParam
     * @param bindingResult
     * @return
     */
    @PostMapping("edit")
    public R edit(@RequestBody EmployeeEditParam employeeEditParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return R.fail("编辑失败，参数错误！");
        }

        int result = employeeService.edit(employeeEditParam);

        return R.ok(result);
    }

    /**
     * 删除员工
     *
     * @param id
     * @param bindingResult
     * @return
     */
    @PostMapping("/delete")
    public R delete(@RequestBody Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return R.fail("删除失败，参数错误！");
        }

        int result = employeeService.delete(id);

        return R.ok(result);
    }
}
