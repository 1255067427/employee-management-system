package com.employee.system.Param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author bluesky
 * @create 2023-04-20-22:05
 */
@Data
public class SearchParam {

    @NotNull
    private String name;

    private Integer pageNum = 1;

    private Integer pageSize = 12;
}
