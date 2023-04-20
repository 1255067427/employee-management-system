package com.employee.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.employee.system.Param.DepartmentEditParam;
import com.employee.system.Param.PageParam;
import com.employee.system.Param.SearchParam;
import com.employee.system.entity.Department;
import com.employee.system.entity.Salary;
import com.employee.system.mapper.DepartmentMapper;
import com.employee.system.mapper.SalaryMapper;
import com.employee.system.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bluesky
 * @create 2023-04-19-21:42
 */
@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 分页查询部门列表
     *
     * @param searchParam
     * @return
     */
    @Override
    public IPage<Department> list(SearchParam searchParam) {

        IPage<Department> page = new Page<>(searchParam.getPageNum(), searchParam.getPageSize());
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();

        if (searchParam.getName().trim() != null || !searchParam.getName().isEmpty()) {
            queryWrapper.like("department_name", searchParam.getName().trim());
        }
        IPage<Department> departmentIPage = departmentMapper.selectPage(page, queryWrapper);

        log.info("DepartmentServiceImpl.list业务结束，结果: {}", "部门列表获取成功！");
        return departmentIPage;

    }

    /**
     * 新增部门
     *
     * @param department
     * @return
     */
    @Override
    public int add(Department department) {

        department.setTotal(0);
        int result = departmentMapper.insert(department);

        log.info("DepartmentServiceImpl.add业务结束，结果: {}", "新增部门成功！");
        return result;
    }

    /**
     * 编辑部门
     *
     * @param departmentEditParam
     * @return
     */
    @Override
    public int edit(DepartmentEditParam departmentEditParam) {

        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", departmentEditParam.getId());

        Department one = departmentMapper.selectOne(queryWrapper);

        one.setDepartmentName(departmentEditParam.getDepartmentName());

        int result = departmentMapper.updateById(one);

        log.info("DepartmentServiceImpl.edit业务结束，结果: {}", "编辑部门成功！");
        return result;
    }

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    @Override
    public int delete(Long id) {

        int result = departmentMapper.deleteById(id);

        log.info("DepartmentServiceImpl.delete业务结束，结果: {}", "删除部门成功！");
        return result;
    }

}
