package com.ruoyi.sy.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 【请填写功能名称】对象 sy_order
 * 
 * @author ruoyi
 * @date 2020-07-21
 */
public class SyOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 报单日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报单日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** 指导老师 */
    @Excel(name = "指导老师")
    private String guidanceTeacher;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderId;

    /** 服务老师 */
    @Excel(name = "服务老师")
    private String serviceTeacher;

    /** 姓名 */
    @Excel(name = "姓名")
    private String userName;

    /** 性别 0 男 1 女 */
    @Excel(name = "性别 0 男 1 女")
    private Long sex;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String cell;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idCard;

    /** 定制工具1 名正言顺 2 终身手表 3 六年鸿运手表 4 戒指 5 数字核能幸福手链 6 若熙手链 7乾坤圆通吊坠 8 风水牌 9 寻龙撅  */
    @Excel(name = "定制工具1 名正言顺 2 终身手表 3 六年鸿运手表 4 戒指 5 数字核能幸福手链 6 若熙手链 7乾坤圆通吊坠 8 风水牌 9 寻龙撅 ")
    private Long customTools;

    /** 账户名 */
    @Excel(name = "账户名")
    private String accountName;

    /** 账户号 */
    @Excel(name = "账户号")
    private String accountNumber;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }
    public void setGuidanceTeacher(String guidanceTeacher) 
    {
        this.guidanceTeacher = guidanceTeacher;
    }

    public String getGuidanceTeacher() 
    {
        return guidanceTeacher;
    }
    public void setOrderId(String orderId) 
    {
        this.orderId = orderId;
    }

    public String getOrderId() 
    {
        return orderId;
    }
    public void setServiceTeacher(String serviceTeacher) 
    {
        this.serviceTeacher = serviceTeacher;
    }

    public String getServiceTeacher() 
    {
        return serviceTeacher;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setSex(Long sex) 
    {
        this.sex = sex;
    }

    public Long getSex() 
    {
        return sex;
    }
    public void setCell(String cell) 
    {
        this.cell = cell;
    }

    public String getCell() 
    {
        return cell;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }
    public void setCustomTools(Long customTools) 
    {
        this.customTools = customTools;
    }

    public Long getCustomTools() 
    {
        return customTools;
    }
    public void setAccountName(String accountName) 
    {
        this.accountName = accountName;
    }

    public String getAccountName() 
    {
        return accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createDate", getCreateDate())
            .append("guidanceTeacher", getGuidanceTeacher())
            .append("orderId", getOrderId())
            .append("serviceTeacher", getServiceTeacher())
            .append("userName", getUserName())
            .append("sex", getSex())
            .append("cell", getCell())
            .append("address", getAddress())
            .append("idCard", getIdCard())
            .append("customTools", getCustomTools())
            .append("remark", getRemark())
            .append("accountName", getAccountName())
            .append("accountNumber", getAccountNumber())
            .toString();
    }
}
