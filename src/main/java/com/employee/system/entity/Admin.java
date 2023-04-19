package com.employee.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author bluesky
 * @create 2023-03-17-23:26
 */
@Data
public class Admin {

    @TableId()
    private Long id;

    @NotNull
    private String adminName;

    @NotNull
    private String adminPassword;
}
