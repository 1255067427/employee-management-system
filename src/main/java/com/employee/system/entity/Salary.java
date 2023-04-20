package com.employee.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author bluesky
 * @create 2023-04-20-22:22
 */
@Data
public class Salary {

    @TableId()
    private Long id;

    @NotNull
    private String employeeName;

    @NotNull
    private String salary;

    @NotNull
    private Long employeeId;

    @NotNull
    private String basicSalary;

    @NotNull
    private String bonus;

    private String subsidy;

    private String overtimeWages;

    private String dockWages;
}
