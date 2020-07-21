package com.ruoyi.api.service;

import com.ruoyi.api.db.model.SysApiUser;
import com.ruoyi.system.api.domain.SysUser;

import javax.servlet.http.HttpServletRequest;


/**
 * 用户 业务层
 * 
 * @author ruoyi
 */
public interface SysApiUserService
{


    /**
     * 根据手机号查询用户信息
     * @param mobile
     * @return
     * @throws Exception
     */
    SysApiUser queryUserByMobile(String mobile) throws Exception;

    int insertUser(HttpServletRequest request);
}
