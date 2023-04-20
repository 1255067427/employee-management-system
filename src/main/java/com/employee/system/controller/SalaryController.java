package com.employee.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.employee.system.Param.SalaryEditParam;
import com.employee.system.Param.SearchParam;
import com.employee.system.entity.Salary;
import com.employee.system.service.SalaryService;
import com.employee.system.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bluesky
 * @create 2023-04-20-22:24
 */
@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @PostMapping("/list")
    public R list(@RequestBody SearchParam searchParam) {

        IPage<Salary> list = salaryService.list(searchParam);

        return R.ok(list);
    }

    /**
     * 编辑部门
     *
     * @param salaryEditParam
     * @param bindingResult
     * @return
     */
    @PostMapping("edit")
    public R edit(@RequestBody SalaryEditParam salaryEditParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return R.fail("编辑失败，参数错误！");
        }

        int result = salaryService.edit(salaryEditParam);

        return R.ok(result);
    }

}
