package com.ruoyi.maintenance.domain;

import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 应用与服务器关联对象 mnt_deploy_server
 * 
 * @author ruoyi
 * @date 2020-07-20
 */
public class MntDeployServer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 部署ID */
    private Long deployId;

    /** 服务ID */
    private Long serverId;

    public void setDeployId(Long deployId) 
    {
        this.deployId = deployId;
    }

    public Long getDeployId() 
    {
        return deployId;
    }
    public void setServerId(Long serverId) 
    {
        this.serverId = serverId;
    }

    public Long getServerId() 
    {
        return serverId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deployId", getDeployId())
            .append("serverId", getServerId())
            .toString();
    }
}
