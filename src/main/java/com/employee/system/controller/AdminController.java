package com.employee.system.controller;

import com.employee.system.Param.RegisterParam;
import com.employee.system.entity.Admin;
import com.employee.system.service.AdminService;
import com.employee.system.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author bluesky
 * @create 2023-03-17-23:34
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 注册
     *
     * @param registerParam
     * @param bindingResult
     * @return
     */
    @PostMapping("/register")
    public R register(@RequestBody RegisterParam registerParam, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return R.fail("注册失败，参数错误！");
        }

        Boolean register = adminService.register(registerParam);
        if (!register) {

            return R.fail("注册失败！");
        }

        return R.ok("注册成功！");
    }

    /**
     * 登录
     *
     * @param request
     * @param adminEntity
     * @param bindingResult
     * @return
     */
    @PostMapping("/login")
    public R login(HttpServletRequest request, @RequestBody Admin adminEntity, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return R.fail("登录失败，参数错误！");
        }

        Admin admin = adminService.login(request, adminEntity);
        if (admin == null) {

            return R.fail("登录失败！");
        }

        return R.ok("登录成功！",admin.getId());
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R logout(HttpServletRequest request) {

        request.getSession().removeAttribute("id");

        return R.ok("退出成功");
    }

}
