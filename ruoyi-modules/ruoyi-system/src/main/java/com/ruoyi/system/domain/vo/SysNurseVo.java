package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.Date;


/**
 * 【请填写功能名称】对象 sys_nurse
 * 
 * @author ruoyi
 * @date 2020-05-22
 */
public class SysNurseVo
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 本次打卡时间 */

    private String nowtime;

    /** 上次打卡时间 */

    private String beforetime;

    /** 1.睡觉 2.喝奶 */

    private String ntype;

    /** 打卡时间间隔 */

    private String ninterval;

    /** 用户id */

    private Long userid;


    private Long isEnd;

    public Long getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Long isEnd) {
        this.isEnd = isEnd;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setNowtime(String nowtime)
    {
        this.nowtime = nowtime;
    }

    public String getNowtime()
    {
        return nowtime;
    }
    public void setBeforetime(String beforetime)
    {
        this.beforetime = beforetime;
    }

    public String getBeforetime()
    {
        return beforetime;
    }
    public void setNtype(String ntype) 
    {
        this.ntype = ntype;
    }

    public String getNtype() 
    {
        return ntype;
    }
    public void setNinterval(String ninterval) 
    {
        this.ninterval = ninterval;
    }

    public String getNinterval() 
    {
        return ninterval;
    }
    public void setUserid(Long userid) 
    {
        this.userid = userid;
    }

    public Long getUserid() 
    {
        return userid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("nowtime", getNowtime())
            .append("beforetime", getBeforetime())
            .append("ntype", getNtype())
            .append("ninterval", getNinterval())
            .append("userid", getUserid())
            .toString();
    }
}
