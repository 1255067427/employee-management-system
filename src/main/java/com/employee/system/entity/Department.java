package com.employee.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author bluesky
 * @create 2023-04-19-21:40
 */
@Data
public class Department {

    @TableId()
    private Long id;

    @NotNull
    private String departmentName;

    private Long total;
}
