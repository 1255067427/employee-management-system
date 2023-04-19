package com.employee.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.employee.system.Param.RegisterParam;
import com.employee.system.entity.Admin;
import com.employee.system.mapper.AdminMapper;
import com.employee.system.service.AdminService;
import com.employee.system.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author bluesky
 * @create 2023-03-17-23:31
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 校验Admin参数是否合法
     *
     * @param registerParam
     * @return
     */
    public Boolean isAdminEmpty(RegisterParam registerParam) {

        String adminName = registerParam.getAdminName().trim();
        String adminPassword1 = registerParam.getAdminPassword1().trim();
        String adminPassword2 = registerParam.getAdminPassword2().trim();

        if (adminName == null || adminPassword1 == null || adminPassword2 == null
                || adminName.trim().length() == 0 || adminPassword1.trim().length() == 0 || adminPassword2.trim().length() == 0) {

            return true;
        }

        return false;
    }

    /**
     * 注册
     *
     * @param registerParam
     * @return
     */
    @Override
    public Boolean register(RegisterParam registerParam) {

        if (isAdminEmpty(registerParam)) {
            log.info("AdminServiceImpl.register业务结束，结果: {}", "注册失败！用户名密码为空！");
            return false;
        }
        // 查找数据库中是否已存在此用户名
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_name", registerParam.getAdminName());

        Admin admin = adminMapper.selectOne(queryWrapper);
        if (admin != null) {

            log.info("AdminServiceImpl.register业务结束，结果: {}", "注册失败！用户名已存在");
            return false;
        }

        // 判断输入的两次密码是否一致
        String adminPassword1 = registerParam.getAdminPassword1();
        String adminPassword2 = registerParam.getAdminPassword2();
        if (!adminPassword1.equals(adminPassword2)) {

            log.info("AdminServiceImpl.register业务结束，结果: {}", "注册失败，两次输入密码不一致！");
            return false;
        }

        // 加密密码后插入数据库中
        String password = MD5Util.encode(adminPassword1);

        Admin result = new Admin();

        result.setAdminName(registerParam.getAdminName());
        result.setAdminPassword(password);
        adminMapper.insert(result);
        log.info("AdminServiceImpl.register业务结束，结果: {}", "注册成功！");

        return true;
    }

    /**
     * 登录
     *
     * @param adminEntity
     * @return
     */
    @Override
    public Admin login(HttpServletRequest request, Admin adminEntity) {

        // 加密密码
        String password = MD5Util.encode(adminEntity.getAdminPassword());

        // 判断登录是否成功
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_name", adminEntity.getAdminName()).eq("admin_password", password);
        Admin admin = adminMapper.selectOne(queryWrapper);
        if (admin == null) {

            log.info("AdminServiceImpl.login业务结束，结果: {}", "登录失败，用户名密码错误！");
            return null;
        }

        request.getSession().setAttribute("id", admin.getId());

        log.info("AdminServiceImpl.login业务结束，结果: {}", "登录成功！");
        return admin;
    }
}
