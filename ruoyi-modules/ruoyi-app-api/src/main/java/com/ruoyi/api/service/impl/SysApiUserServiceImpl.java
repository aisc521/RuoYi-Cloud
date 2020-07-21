package com.ruoyi.api.service.impl;

import com.ruoyi.api.db.mapper.SysUserMapper;
import com.ruoyi.api.db.model.SysApiUser;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.domain.SysUser;

import com.ruoyi.api.service.SysApiUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * 用户 业务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysApiUserServiceImpl implements SysApiUserService
{
    private static final Logger log = LoggerFactory.getLogger(SysApiUserServiceImpl.class);

    @Autowired
    private SysUserMapper userMapper;


    @Override
    public SysApiUser queryUserByMobile(String mobile) throws Exception {
        return userMapper.queryUserByMobile(mobile);
    }

    @Override
    public int insertUser(HttpServletRequest request) {
        SysApiUser insertUser = new SysApiUser();
        insertUser.setDeptId(Long.valueOf(100));//设置默认部门
        insertUser.setUserName(request.getParameter("nikeName"));//昵称
        insertUser.setNickName(request.getParameter("nikeName"));//昵称
        insertUser.setUserType("11");//用户类型 11  app用户
        insertUser.setEmail(request.getParameter("email"));
        insertUser.setPhonenumber(request.getParameter("mobile"));
        insertUser.setSex(request.getParameter("sex"));
        //insertUser.setAvatar();头像地址
        insertUser.setPassword(SecurityUtils.encryptPassword(request.getParameter("password")));
        insertUser.setStatus("0");
        insertUser.setDelFlag("0");
        insertUser.setCreateTime(new Date());
        insertUser.setUpdateTime(new Date());
        return userMapper.insertUser(insertUser);
    }
}
