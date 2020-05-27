package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.mail.SendFileMail;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.domain.SysUser;
import com.ruoyi.system.domain.SysEmailConfig;
import com.ruoyi.system.domain.vo.SysNurseVo;
import com.ruoyi.system.mapper.SysEmailConfigMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysNurseMapper;
import com.ruoyi.system.domain.SysNurse;
import com.ruoyi.system.service.ISysNurseService;

import javax.mail.MessagingException;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-22
 */
@Service
public class SysNurseServiceImpl implements ISysNurseService 
{
    @Autowired
    private SysNurseMapper sysNurseMapper;

    @Autowired
    private SysEmailConfigMapper sysEmailConfigMapper;

    @Autowired
    private SysUserMapper sysUserMapper;
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public SysNurse selectSysNurseById(Long id)
    {
        return sysNurseMapper.selectSysNurseById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysNurse 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SysNurseVo> selectSysNurseList(SysNurse sysNurse)
    {
        return sysNurseMapper.selectSysNurseList(sysNurse);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysNurse 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertSysNurse(SysNurse sysNurse)
    {
        Long userId = SecurityUtils.getLoginUser().getUserId();
        Date nowDate = new Date();

        SysNurse sysNursequery = new SysNurse();
        sysNursequery.setUserid(userId);
        if("2".equals(sysNurse.getNtype())){
            sysNursequery.setNtype("0");
        }
        if("0".equals(sysNurse.getNtype())){
            sysNursequery.setNtype("2");
        }
        if("1".equals(sysNurse.getNtype())){
            sysNursequery.setNtype("3");
        }
        if("3".equals(sysNurse.getNtype())){
            sysNursequery.setNtype("1");
        }
        SysNurse sysNurse1 = sysNurseMapper.selectSysNurseByIdFroLastInfo(sysNursequery);
        if(sysNurse1 != null){
            sysNurse.setBeforetime(sysNurse1.getNowtime());
            sysNurse.setNinterval(DateUtils.getDatePoor(nowDate,sysNurse1.getNowtime()));
            if("2".equals(sysNurse.getNtype()) || "3".equals(sysNurse.getNtype())){
                sysNurse1.setIsEnd(Long.valueOf(0));
                sysNurseMapper.updateSysNurse(sysNurse1);
                sysNurse.setIsEnd(Long.valueOf(0));
            }else{
                sysNurse.setIsEnd(Long.valueOf(1));
            }
        }else{
            sysNurse.setIsEnd(Long.valueOf(1));
        }
        sysNurse.setUserid(userId);
        sysNurse.setNowtime(nowDate);
        try {
            babyEmailSend(userId,sysNurse.getNtype());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return sysNurseMapper.insertSysNurse(sysNurse);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysNurse 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSysNurse(SysNurse sysNurse)
    {
        return sysNurseMapper.updateSysNurse(sysNurse);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteSysNurseByIds(Long[] ids)
    {
        return sysNurseMapper.deleteSysNurseByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteSysNurseById(Long id)
    {
        return sysNurseMapper.deleteSysNurseById(id);
    }

    @Override
    public boolean checkAdd(SysNurse sysNurse) {
        Long userId = SecurityUtils.getLoginUser().getUserId();
        sysNurse.setUserid(userId);
        SysNurse sysNurse1 = sysNurseMapper.checkAdd(sysNurse);
        if(sysNurse1 != null){
            return false;
        }
        return true;
    }


    /**
     发送邮件  邮件类型为babyemail
     查询 邮箱配置信息
     */
    private void babyEmailSend(Long userId,String type) throws MessagingException {
        SysEmailConfig query = new SysEmailConfig();
        query.setEmailType("0");
        List<SysEmailConfig> sysEmailConfigList = sysEmailConfigMapper.selectSysEmailConfigList(query);
        List emailList = new ArrayList();
        for (int i = 0; i < sysEmailConfigList.size(); i++) {
            emailList.add(sysEmailConfigList.get(i).getReciveEmailAdress());
        }
        if(sysEmailConfigList.size() > 0){
            String content = "";
            SysUser sysUser = sysUserMapper.selectUserById(userId);
            switch(type){
                case "0":
                    content="宝妈:" + sysUser.getNickName() + "和她的宝宝又开始了" + "睡觉" +"模式";
                    break;
                case "1":
                    content="宝妈:" + sysUser.getNickName() + "和她的宝宝又开始了" + "奶牛" +"模式";
                    break;
                case "2":
                    content="宝妈:" + sysUser.getNickName() + "和她的宝宝又开始了" + "玩玩玩" +"模式";
                    break;
                case "3":
                    content="宝妈:" + sysUser.getNickName() + "和她的宝宝又开始了" + "饱饱饱" +"模式";
                    break;
            }
            String reciveEmail = StringUtils.listToString1(emailList,",");
            SendFileMail sendFileMail = new SendFileMail();
            sendFileMail.SendMail(sysEmailConfigList.get(0).getSmtpCom(),sysEmailConfigList.get(0).getSendEmailAddress(),
                    reciveEmail,"","",sysEmailConfigList.get(0).getTheme(),
                    content,null,sysEmailConfigList.get(0).getEnclosureName());
        }

    }

}
