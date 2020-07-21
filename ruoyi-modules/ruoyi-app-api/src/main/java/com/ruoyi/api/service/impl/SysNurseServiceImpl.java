package com.ruoyi.api.service.impl;

import com.ruoyi.api.db.mapper.SysNurseMapper;
import com.ruoyi.api.db.model.SysNurse;
import com.ruoyi.api.dto.SysNurseDto;
import com.ruoyi.api.service.SysNurseService;
import com.ruoyi.common.core.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SysNurseServiceImpl implements SysNurseService {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");//注意月份是MM
    @Autowired
    private SysNurseMapper sysNurseMapper;
    @Override
    public List<SysNurseDto> selectSysNurseList(String userId,String queryType) {
        return sysNurseMapper.selectSysNurseList(userId,queryType);
    }

    @Override
    public boolean checkAdd(SysNurse sysNurse) {
        SysNurseDto sysNurse1 = sysNurseMapper.checkAdd(sysNurse);
        if(sysNurse1 != null){
            return false;
        }
        return true;
    }

    @Override
    public int insertSysNurse(SysNurse sysNurse, String dateTime) throws ParseException {
        Date nowDate = new Date();
        SysNurse sysNursequery = new SysNurse();
        sysNursequery.setUserid(sysNurse.getUserid());
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
        if(StringUtils.isNoneBlank(dateTime)){

            Date date = simpleDateFormat.parse(dateTime);

            sysNurse.setNowtime(date);
        }else{
            sysNurse.setNowtime(nowDate);
        }
        SysNurse sysNurse1 = sysNurseMapper.selectSysNurseByIdFroLastInfo(sysNursequery);
        if(sysNurse1 != null){
            sysNurse.setBeforetime(sysNurse1.getNowtime());
            if(StringUtils.isNoneBlank(dateTime)){

                Date date = simpleDateFormat.parse(dateTime);
                sysNurse.setNinterval(DateUtils.getDatePoor(date,sysNurse1.getNowtime()));
            }else{
                sysNurse.setNinterval(DateUtils.getDatePoor(nowDate,sysNurse1.getNowtime()));
            }
            if("2".equals(sysNurse.getNtype()) || "3".equals(sysNurse.getNtype())){
                sysNurse1.setIsend(0);
                sysNurseMapper.updateSysNurse(sysNurse1);
                sysNurse.setIsend(0);
            }else{
                sysNurse.setIsend(1);
            }
        }else{
            int count = sysNurseMapper.selentCount(sysNurse.getUserid());
            if(count == 0){
                sysNurse.setIsend(1);
            }else{
                sysNurse.setIsend(0);
            }
        }

        int id = sysNurseMapper.insertSysNurse(sysNurse);
        //发送邮件信息
        return sysNurse.getId();
    }

    @Override
    public SysNurseDto querySysNurseById(int i) {
        return sysNurseMapper.querySysNurseById(i);
    }
}
