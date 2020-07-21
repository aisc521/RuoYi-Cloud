package com.ruoyi.maintenance.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 部署历史管理对象 mnt_deploy_history
 * 
 * @author ruoyi
 * @date 2020-07-21
 */
public class MntDeployHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String historyId;

    /** 应用名称 */
    @Excel(name = "应用名称")
    private String appName;

    /** 部署日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "部署日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deployDate;

    /** 部署用户 */
    @Excel(name = "部署用户")
    private String deployUser;

    /** 服务器IP */
    @Excel(name = "服务器IP")
    private String ip;

    /** 部署编号 */
    @Excel(name = "部署编号")
    private Long deployId;

    public void setHistoryId(String historyId) 
    {
        this.historyId = historyId;
    }

    public String getHistoryId() 
    {
        return historyId;
    }
    public void setAppName(String appName) 
    {
        this.appName = appName;
    }

    public String getAppName() 
    {
        return appName;
    }
    public void setDeployDate(Date deployDate) 
    {
        this.deployDate = deployDate;
    }

    public Date getDeployDate() 
    {
        return deployDate;
    }
    public void setDeployUser(String deployUser) 
    {
        this.deployUser = deployUser;
    }

    public String getDeployUser() 
    {
        return deployUser;
    }
    public void setIp(String ip) 
    {
        this.ip = ip;
    }

    public String getIp() 
    {
        return ip;
    }
    public void setDeployId(Long deployId) 
    {
        this.deployId = deployId;
    }

    public Long getDeployId() 
    {
        return deployId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("historyId", getHistoryId())
            .append("appName", getAppName())
            .append("deployDate", getDeployDate())
            .append("deployUser", getDeployUser())
            .append("ip", getIp())
            .append("deployId", getDeployId())
            .toString();
    }
}
