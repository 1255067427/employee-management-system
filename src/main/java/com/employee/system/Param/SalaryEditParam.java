package com.employee.system.Param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author bluesky
 * @create 2023-04-20-22:50
 */
@Data
public class SalaryEditParam {

    @NotNull()
    private Long id;

    @NotNull
    private String salary;

    @NotNull
    private String employeeId;

    @NotNull
    private String basicSalary;

    @NotNull
    private String bonus;

    @NotNull
    private String subsidy;

    @NotNull
    private String overtimeWages;

    @NotNull
    private String dockWages;
}
