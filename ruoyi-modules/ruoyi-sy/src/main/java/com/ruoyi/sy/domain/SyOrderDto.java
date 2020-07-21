package com.ruoyi.sy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 【请填写功能名称】对象 sy_order
 * 
 * @author ruoyi
 * @date 2020-07-21
 */
public class SyOrderDto extends BaseEntity
{
    private String userName;
    private String sex;
    private String cell;
    private String address;
    private String idCard;
    private String customTools;
    private String accountName;
    private String accountNumber;

    private String orderName;
    private String order_sex;
    private String order_birthday;
    private String order_zodiac;
    private String order_time;
    private String order_father_name;
    private String order_mother_name;
    private String order_bs_name;
    private String words_tao;
    private String remark;

    private String lettering_not;
    private String jz_size;
    private String type_name;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getCustomTools() {
        return customTools;
    }

    public void setCustomTools(String customTools) {
        this.customTools = customTools;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrder_sex() {
        return order_sex;
    }

    public void setOrder_sex(String order_sex) {
        this.order_sex = order_sex;
    }

    public String getOrder_birthday() {
        return order_birthday;
    }

    public void setOrder_birthday(String order_birthday) {
        this.order_birthday = order_birthday;
    }

    public String getOrder_zodiac() {
        return order_zodiac;
    }

    public void setOrder_zodiac(String order_zodiac) {
        this.order_zodiac = order_zodiac;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getOrder_father_name() {
        return order_father_name;
    }

    public void setOrder_father_name(String order_father_name) {
        this.order_father_name = order_father_name;
    }

    public String getOrder_mother_name() {
        return order_mother_name;
    }

    public void setOrder_mother_name(String order_mother_name) {
        this.order_mother_name = order_mother_name;
    }

    public String getOrder_bs_name() {
        return order_bs_name;
    }

    public void setOrder_bs_name(String order_bs_name) {
        this.order_bs_name = order_bs_name;
    }

    public String getWords_tao() {
        return words_tao;
    }

    public void setWords_tao(String words_tao) {
        this.words_tao = words_tao;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLettering_not() {
        return lettering_not;
    }

    public void setLettering_not(String lettering_not) {
        this.lettering_not = lettering_not;
    }

    public String getJz_size() {
        return jz_size;
    }

    public void setJz_size(String jz_size) {
        this.jz_size = jz_size;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
}
