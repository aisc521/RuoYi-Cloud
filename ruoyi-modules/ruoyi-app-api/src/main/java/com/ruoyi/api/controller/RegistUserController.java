package com.ruoyi.api.controller;

import com.ruoyi.api.db.model.SysApiUser;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.annotation.Inner;

import com.ruoyi.api.service.SysApiUserService;
import com.ruoyi.common.security.utils.SecurityUtils;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.util.Date;

@RestController
@RequestMapping("regist")
@Logger
public class RegistUserController {

    @Autowired
    private SysApiUserService sysUserService;

    @Inner(false)
    @GetMapping(value = "/addUser")
    public AjaxResult getInfo(HttpServletRequest request) throws Exception {
        if(!verificationParam(request)){
            return AjaxResult.error("请求参数不能为空,请重新确认");
        }
        //查询手机号是否已经存在
        SysApiUser sysUser = sysUserService.queryUserByMobile(request.getParameter("mobile"));
        if(sysUser != null){
            return AjaxResult.error("手机号已经注册，请更换手机号，或者找回密码");
        }
        int i = sysUserService.insertUser(request);
        if(i <= 0){
            return AjaxResult.success("注册失败,请重新操作");
        }
        return AjaxResult.success("注册成功");
    }

    public boolean verificationParam(HttpServletRequest request){
        String nikeName  = request.getParameter("nikeName");
        String sex  = request.getParameter("sex");
        String age  = request.getParameter("age");
        String mobile  = request.getParameter("mobile");
        String email  = request.getParameter("email");
        String password  = request.getParameter("password");
        if(StringUtils.isEmpty(nikeName) ||
           StringUtils.isEmpty(sex) ||
           StringUtils.isEmpty(age) ||
           StringUtils.isEmpty(mobile) ||
           StringUtils.isEmpty(email) ||
           StringUtils.isEmpty(password)){
            return false;
        }
        return true;
    }
}
