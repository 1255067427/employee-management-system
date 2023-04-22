package com.employee.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.employee.system.Param.DepartmentEditParam;
import com.employee.system.Param.PageParam;
import com.employee.system.Param.SearchParam;
import com.employee.system.entity.Department;
import com.employee.system.entity.Employee;
import com.employee.system.service.DepartmentService;
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
 * @create 2023-04-19-21:41
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 分页查询部门列表
     *
     * @param searchParam
     * @return
     */
    @PostMapping("/list")
    public R list(@RequestBody SearchParam searchParam) {

        IPage<Department> list = departmentService.list(searchParam);

        return R.ok(list);
    }

    /**
     * 新增部门
     *
     * @param department
     * @param bindingResult
     * @return
     */
    @PostMapping("add")
    public R add(@RequestBody Department department, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return R.fail("添加失败，参数错误！");
        }

        int result = departmentService.add(department);

        return R.ok(result);
    }

    /**
     * 编辑部门
     *
     * @param departmentEditParam
     * @param bindingResult
     * @return
     */
    @PostMapping("edit")
    public R edit(@RequestBody DepartmentEditParam departmentEditParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return R.fail("编辑失败，参数错误！");
        }

        int result = departmentService.edit(departmentEditParam);

        return R.ok(result);

    }

    /**
     * 删除部门
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

        int result = departmentService.delete(id);

        return R.ok(result);
    }

    /**
     * 获取所有部门
     *
     * @return
     */
    @PostMapping("/depList")
    public R depList() {

        List<Department> list = departmentService.depList();

        return R.ok(list);
    }

    /**
     * 回显
     *
     * @param id
     * @param bindingResult
     * @return
     */
    @PostMapping("/back")
    public R back(@RequestBody Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return R.fail("删除失败，参数错误！");
        }

        Department department = departmentService.back(id);

        return R.ok(department);
    }
}
