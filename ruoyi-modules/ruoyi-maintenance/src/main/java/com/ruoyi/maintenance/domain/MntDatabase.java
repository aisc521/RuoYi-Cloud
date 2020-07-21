package com.ruoyi.maintenance.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 数据库管理对象 mnt_database
 * 
 * @author ruoyi
 * @date 2020-07-20
 */
public class MntDatabase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer dbId;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** jdbc连接 */
    @Excel(name = "jdbc连接")
    private String jdbcUrl;

    /** 账号 */
    @Excel(name = "账号")
    private String userName;

    /** 密码 */
    @Excel(name = "密码")
    private String pwd;

    public Integer getDbId() {
        return dbId;
    }

    public void setDbId(Integer dbId) {
        this.dbId = dbId;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setJdbcUrl(String jdbcUrl) 
    {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUrl() 
    {
        return jdbcUrl;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setPwd(String pwd) 
    {
        this.pwd = pwd;
    }

    public String getPwd() 
    {
        return pwd;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("dbId", getDbId())
            .append("name", getName())
            .append("jdbcUrl", getJdbcUrl())
            .append("userName", getUserName())
            .append("pwd", getPwd())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
