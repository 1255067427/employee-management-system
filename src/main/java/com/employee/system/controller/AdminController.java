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

            return R.fail("注册失败！参数错误！");
        }

        Boolean register = adminService.register(registerParam);
        if (!register) {

            return R.fail("注册失败！");
        }

        return R.ok("注册成功！");
    }

    /**
     * 登录
     * @param admin
     * @param bindingResult
     * @return
     */
    @PostMapping("/login")
    public R login(HttpServletRequest request, @RequestBody Admin admin, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {

            return R.fail("登录失败！参数错误！");
        }

        Admin admin1 = adminService.login(admin);
        if (admin==null) {

            return R.fail("登录失败！");
        }

        return R.ok("登陆成功！",admin1);
    }

    @PostMapping("/logout")
    public R logout(@RequestBody Long id,BindingResult bindingResult){

        if (bindingResult.hasErrors()) {

            return R.fail("登录失败！参数错误！");
        }



        return R.ok();
    }

}
