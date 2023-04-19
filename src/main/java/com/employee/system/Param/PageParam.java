package com.employee.system.Param;

import lombok.Data;


/**
 * @author bluesky
 * @create 2023-04-18-22:22
 */
@Data
public class PageParam {

    private Integer pageNum = 1;

    private Integer pageSize = 15;
}
