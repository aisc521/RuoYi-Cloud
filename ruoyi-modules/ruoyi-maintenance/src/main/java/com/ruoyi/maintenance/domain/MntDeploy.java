package com.ruoyi.maintenance.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 部署管理对象 mnt_deploy
 * 
 * @author ruoyi
 * @date 2020-07-17
 */
public class MntDeploy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long deployId;

    /** 应用编号 */
    @Excel(name = "应用编号")
    private Long appId;

    public void setDeployId(Long deployId) 
    {
        this.deployId = deployId;
    }

    public Long getDeployId() 
    {
        return deployId;
    }
    public void setAppId(Long appId) 
    {
        this.appId = appId;
    }

    public Long getAppId() 
    {
        return appId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deployId", getDeployId())
            .append("appId", getAppId())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
