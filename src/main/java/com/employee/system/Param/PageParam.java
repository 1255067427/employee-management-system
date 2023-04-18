package com.employee.system.Param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author bluesky
 * @create 2023-04-18-22:22
 */
@Data
public class PageParam {

    @NotNull
    private Integer pageNum;

    @NotNull
    private Integer pageSize;
}
