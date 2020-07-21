package com.ruoyi.api.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


public class SysNurseDto implements Serializable {
    @Id
    private Integer id;

    /**
     * 本次打卡时间
     */

    private String nowTime;

    /**
     * 上次打卡时间
     */

    private String beforeTime;

    /**
     * 1.睡觉 2.喝奶
     */
    private String ntype;

    /**
     * 打卡时间间隔
     */
    private String ninterval;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 是否结束
     */

    private Integer isend;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }



    /**
     * 获取1.睡觉 2.喝奶
     *
     * @return ntype - 1.睡觉 2.喝奶
     */
    public String getNtype() {
        return ntype;
    }

    /**
     * 设置1.睡觉 2.喝奶
     *
     * @param ntype 1.睡觉 2.喝奶
     */
    public void setNtype(String ntype) {
        this.ntype = ntype;
    }

    /**
     * 获取打卡时间间隔
     *
     * @return ninterval - 打卡时间间隔
     */
    public String getNinterval() {
        return ninterval;
    }

    /**
     * 设置打卡时间间隔
     *
     * @param ninterval 打卡时间间隔
     */
    public void setNinterval(String ninterval) {
        this.ninterval = ninterval;
    }

    /**
     * 获取用户id
     *
     * @return userid - 用户id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置用户id
     *
     * @param userid 用户id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取是否结束
     *
     * @return isEnd - 是否结束
     */
    public Integer getIsend() {
        return isend;
    }

    /**
     * 设置是否结束
     *
     * @param isend 是否结束
     */
    public String getNowTime() {
        return nowTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
    }

    public String getBeforeTime() {
        return beforeTime;
    }

    public void setBeforeTime(String beforeTime) {
        this.beforeTime = beforeTime;
    }

    public void setIsend(Integer isend) {
        this.isend = isend;
    }
}