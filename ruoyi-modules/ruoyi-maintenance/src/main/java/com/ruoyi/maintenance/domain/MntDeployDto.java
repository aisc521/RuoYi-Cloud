package com.ruoyi.maintenance.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 部署管理对象 mnt_deploy
 * 
 * @author ruoyi
 * @date 2020-07-17
 */
public class MntDeployDto extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long deployId;

    /** 应用编号 */
    @Excel(name = "应用名称")
    private String appName;

    /** 应用编号 */
    @Excel(name = "服务器名称")
    private String serverName;


    /** 应用编号 */
    @Excel(name = "部署日期")
    private Date createDate;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setDeployId(Long deployId)
    {
        this.deployId = deployId;
    }

    public Long getDeployId() 
    {
        return deployId;
    }


}
