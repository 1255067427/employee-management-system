package com.employee.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author bluesky
 * @create 2023-04-17-23:20
 */
@Data
public class Employee {

    @TableId()
    private Long id;

    @NotNull
    private String employeeName;

    @NotNull
    private Integer age;

    @NotNull
    private Long departmentId;

    private String salary;

    @NotNull
    private Long salaryId;
}
