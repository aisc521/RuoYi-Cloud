package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.system.domain.vo.SysNurseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysNurseMapper;
import com.ruoyi.system.domain.SysNurse;
import com.ruoyi.system.service.ISysNurseService;

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
        Date nowDate = new Date();

        SysNurse sysNursequery = new SysNurse();
        sysNursequery.setUserid(Long.valueOf(1));
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
        sysNurse.setUserid(Long.valueOf(1));
        sysNurse.setNowtime(nowDate);

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
        sysNurse.setUserid(Long.valueOf(1));
        SysNurse sysNurse1 = sysNurseMapper.checkAdd(sysNurse);
        if(sysNurse1 != null){
            return false;
        }
        return true;
    }
}
