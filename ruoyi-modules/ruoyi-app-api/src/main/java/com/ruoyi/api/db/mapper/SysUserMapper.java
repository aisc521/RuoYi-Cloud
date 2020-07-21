package com.ruoyi.api.db.mapper;

import com.ruoyi.api.db.model.SysApiUser;
import org.apache.ibatis.annotations.Param;


public interface SysUserMapper  {
    SysApiUser queryUserByMobile(@Param("phonenumber") String mobile);

    int insertUser(SysApiUser insertUser);
}