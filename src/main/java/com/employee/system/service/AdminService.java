package com.employee.system.service;

import com.employee.system.Param.RegisterParam;
import com.employee.system.entity.Admin;

/**
 * @author bluesky
 * @create 2023-03-17-23:30
 */
public interface AdminService {

    /**
     * 注册
     *
     * @param registerParam
     * @return
     */
    Boolean register(RegisterParam registerParam);

    /**
     * 登录
     * @param admin
     * @return
     */
    Admin login(Admin admin);
}
