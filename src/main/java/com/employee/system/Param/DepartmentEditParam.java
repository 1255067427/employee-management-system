package com.employee.system.Param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author bluesky
 * @create 2023-04-19-23:20
 */
@Data
public class DepartmentEditParam {

    @NotNull
    private Long id;

    @NotNull
    private String departmentName;
}
