package com.employee.system.Param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author bluesky
 * @create 2023-04-21-0:15
 */
@Data
public class EmployeeEditParam {

    @NotNull
    private Long id;

    @NotNull
    private String employeeName;

    @NotNull
    private Integer age;

    @NotNull
    private Long departmentId;
}
