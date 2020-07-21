package com.ruoyi.api.controller;

import com.ruoyi.api.db.model.SysApiUser;
import com.ruoyi.api.service.SysApiUserService;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.exception.ValidateCodeException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.annotation.Inner;
import com.ruoyi.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("login")
public class LoginController {


    @Autowired
    private RedisService redisService;

    @Autowired
    private SysApiUserService sysApiUserService;
    @Inner(false)
    @PostMapping(value = "/toLogin")
    public AjaxResult login(HttpServletRequest request) throws Exception {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        String uuid = request.getParameter("uuid");
        if(StringUtils.isEmpty(code)){
            return AjaxResult.error("验证码不能为空");
        }
        if(StringUtils.isEmpty(userName)){
            return AjaxResult.error("用户名不能为空");
        }
        if(StringUtils.isEmpty(password)){
            return AjaxResult.error("密码不能为空");
        }
        if(StringUtils.isEmpty(uuid)){
            return AjaxResult.error("uuid不能为空");
        }

        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisService.getCacheObject(verifyKey);
        redisService.deleteObject(verifyKey);

        if (!code.equalsIgnoreCase(captcha))
        {
            return AjaxResult.error("验证码错误");
        }

        SysApiUser sysApiUser = sysApiUserService.queryUserByMobile(userName);
        if(sysApiUser == null){
            return  AjaxResult.error("改用户没有注册,请先注册后在登陆");
        }
        String passwordReal = sysApiUser.getPassword();
        if(!SecurityUtils.matchesPassword(password,passwordReal)){
            return  AjaxResult.error("密码错误");
        }
        return AjaxResult.success("登陆成功",sysApiUser);
    }


}
