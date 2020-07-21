package com.ruoyi.maintenance.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 服务器管理对象 mnt_server
 * 
 * @author ruoyi
 * @date 2020-07-16
 */
public class MntServer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long serverId;

    /** 账号 */
    @Excel(name = "账号")
    private String account;

    /** IP地址 */
    @Excel(name = "IP地址")
    private String ip;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 端口 */
    @Excel(name = "端口")
    private Long port;

    public void setServerId(Long serverId) 
    {
        this.serverId = serverId;
    }

    public Long getServerId() 
    {
        return serverId;
    }
    public void setAccount(String account) 
    {
        this.account = account;
    }

    public String getAccount() 
    {
        return account;
    }
    public void setIp(String ip) 
    {
        this.ip = ip;
    }

    public String getIp() 
    {
        return ip;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setPort(Long port) 
    {
        this.port = port;
    }

    public Long getPort() 
    {
        return port;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("serverId", getServerId())
            .append("account", getAccount())
            .append("ip", getIp())
            .append("name", getName())
            .append("password", getPassword())
            .append("port", getPort())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
