package com.employee.system.Param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author bluesky
 * @create 2023-03-18-0:18
 */
@Data
public class RegisterParam {

    @NotNull
    private String adminName;

    @NotNull
    private String adminPassword1;

    @NotNull
    private String adminPassword2;

}
