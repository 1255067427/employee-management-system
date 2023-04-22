package com.employee.system.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author bluesky
 * @create 2023-04-22-22:03
 */
@Data
public class EmployeeListVo {

    @TableId()
    private Long id;

    @NotNull
    private String employeeName;

    @NotNull
    private Integer age;

    @NotNull
    private String departmentName;

    private String salary;
}
