package com.ruoyi.system.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
;

/**
 * 【请填写功能名称】对象 sys_email_config
 * 
 * @author ruoyi
 * @date 2020-05-26
 */
public class SysEmailConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** smtpCom */
    @Excel(name = "smtpCom")
    private String smtpCom;

    /** 发送者邮箱 */
    @Excel(name = "发送者邮箱")
    private String sendEmailAddress;

    /** 接收者邮箱 */
    @Excel(name = "接收者邮箱")
    private String reciveEmailAdress;

    /** 主题 */
    @Excel(name = "主题")
    private String theme;

    /** 邮箱类型 */
    @Excel(name = "邮箱类型")
    private String emailType;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 名称 */
    @Excel(name = "名称")
    private String enclosureName;

    /** 文件地址 */
    @Excel(name = "文件地址")
    private String fileAdress;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSmtpCom(String smtpCom) 
    {
        this.smtpCom = smtpCom;
    }

    public String getSmtpCom() 
    {
        return smtpCom;
    }
    public void setSendEmailAddress(String sendEmailAddress) 
    {
        this.sendEmailAddress = sendEmailAddress;
    }

    public String getSendEmailAddress() 
    {
        return sendEmailAddress;
    }
    public void setReciveEmailAdress(String reciveEmailAdress) 
    {
        this.reciveEmailAdress = reciveEmailAdress;
    }

    public String getReciveEmailAdress() 
    {
        return reciveEmailAdress;
    }
    public void setTheme(String theme) 
    {
        this.theme = theme;
    }

    public String getTheme() 
    {
        return theme;
    }
    public void setEmailType(String emailType) 
    {
        this.emailType = emailType;
    }

    public String getEmailType() 
    {
        return emailType;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setEnclosureName(String enclosureName) 
    {
        this.enclosureName = enclosureName;
    }

    public String getEnclosureName() 
    {
        return enclosureName;
    }
    public void setFileAdress(String fileAdress) 
    {
        this.fileAdress = fileAdress;
    }

    public String getFileAdress() 
    {
        return fileAdress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("smtpCom", getSmtpCom())
            .append("sendEmailAddress", getSendEmailAddress())
            .append("reciveEmailAdress", getReciveEmailAdress())
            .append("theme", getTheme())
            .append("emailType", getEmailType())
            .append("content", getContent())
            .append("enclosureName", getEnclosureName())
            .append("fileAdress", getFileAdress())
            .toString();
    }
}
