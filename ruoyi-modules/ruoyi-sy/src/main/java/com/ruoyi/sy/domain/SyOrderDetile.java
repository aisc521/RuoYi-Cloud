package com.ruoyi.sy.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 【请填写功能名称】对象 sy_order_detile
 * 
 * @author ruoyi
 * @date 2020-07-21
 */
public class SyOrderDetile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 定制工具类型 */
    @Excel(name = "定制工具类型")
    private Long typeId;

    /** 定制人姓名 */
    @Excel(name = "定制人姓名")
    private String orderName;

    /** 定制人性别 */
    @Excel(name = "定制人性别")
    private Long orderSex;

    /** 定制人阳历出生年月日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "定制人阳历出生年月日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderBirthday;

    /** 定制人生肖 */
    @Excel(name = "定制人生肖")
    private String orderZodiac;

    /** 定制人时辰 */
    @Excel(name = "定制人时辰")
    private String orderTime;

    /** 定制人父亲姓名 */
    @Excel(name = "定制人父亲姓名")
    private String orderFatherName;

    /** 定制人母亲姓名 */
    @Excel(name = "定制人母亲姓名")
    private String orderMotherName;

    /** 定制人兄弟姐妹姓名 */
    @Excel(name = "定制人兄弟姐妹姓名")
    private String orderBsName;

    /** 禁忌字 */
    @Excel(name = "禁忌字")
    private String wordsTao;

    /** 是否刻字0 是 1 否 */
    @Excel(name = "是否刻字0 是 1 否")
    private Long letteringNot;

    /** 风水牌 和 寻龙撅得 小类 存对应中文名 */
    @Excel(name = "风水牌 和 寻龙撅得 小类 存对应中文名")
    private String typeName;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** 戒指尺寸 */
    @Excel(name = "戒指尺寸")
    private String jzSize;

    /** 订单id */
    @Excel(name = "订单id")
    private String orderId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }
    public void setOrderName(String orderName) 
    {
        this.orderName = orderName;
    }

    public String getOrderName() 
    {
        return orderName;
    }
    public void setOrderSex(Long orderSex) 
    {
        this.orderSex = orderSex;
    }

    public Long getOrderSex() 
    {
        return orderSex;
    }
    public void setOrderBirthday(Date orderBirthday) 
    {
        this.orderBirthday = orderBirthday;
    }

    public Date getOrderBirthday() 
    {
        return orderBirthday;
    }
    public void setOrderZodiac(String orderZodiac) 
    {
        this.orderZodiac = orderZodiac;
    }

    public String getOrderZodiac() 
    {
        return orderZodiac;
    }
    public void setOrderTime(String orderTime) 
    {
        this.orderTime = orderTime;
    }

    public String getOrderTime() 
    {
        return orderTime;
    }
    public void setOrderFatherName(String orderFatherName) 
    {
        this.orderFatherName = orderFatherName;
    }

    public String getOrderFatherName() 
    {
        return orderFatherName;
    }
    public void setOrderMotherName(String orderMotherName) 
    {
        this.orderMotherName = orderMotherName;
    }

    public String getOrderMotherName() 
    {
        return orderMotherName;
    }
    public void setOrderBsName(String orderBsName) 
    {
        this.orderBsName = orderBsName;
    }

    public String getOrderBsName() 
    {
        return orderBsName;
    }
    public void setWordsTao(String wordsTao)
    {
        this.wordsTao = wordsTao;
    }

    public String getWordsTao()
    {
        return wordsTao;
    }
    public void setLetteringNot(Long letteringNot) 
    {
        this.letteringNot = letteringNot;
    }

    public Long getLetteringNot() 
    {
        return letteringNot;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }
    public void setJzSize(String jzSize) 
    {
        this.jzSize = jzSize;
    }

    public String getJzSize() 
    {
        return jzSize;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("typeId", getTypeId())
            .append("orderName", getOrderName())
            .append("orderSex", getOrderSex())
            .append("orderBirthday", getOrderBirthday())
            .append("orderZodiac", getOrderZodiac())
            .append("orderTime", getOrderTime())
            .append("orderFatherName", getOrderFatherName())
            .append("orderMotherName", getOrderMotherName())
            .append("orderBsName", getOrderBsName())
            .append("wordsTao", getWordsTao())
            .append("remark", getRemark())
            .append("letteringNot", getLetteringNot())
            .append("typeName", getTypeName())
            .append("createDate", getCreateDate())
            .append("jzSize", getJzSize())
            .append("orderId", getOrderId())
            .toString();
    }
}
