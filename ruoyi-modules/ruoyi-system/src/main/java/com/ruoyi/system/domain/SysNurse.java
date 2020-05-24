package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 【请填写功能名称】对象 sys_nurse
 * 
 * @author ruoyi
 * @date 2020-05-22
 */
public class SysNurse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 本次打卡时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "本次打卡时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date nowtime;

    /** 上次打卡时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "上次打卡时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date beforetime;

    /** 1.睡觉 2.喝奶 */
    @Excel(name = "1.睡觉 2.喝奶")
    private String ntype;

    /** 打卡时间间隔 */
    @Excel(name = "打卡时间间隔")
    private String ninterval;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userid;

    @Excel(name = "是否结束")
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
    public void setNowtime(Date nowtime) 
    {
        this.nowtime = nowtime;
    }

    public Date getNowtime() 
    {
        return nowtime;
    }
    public void setBeforetime(Date beforetime) 
    {
        this.beforetime = beforetime;
    }

    public Date getBeforetime() 
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
