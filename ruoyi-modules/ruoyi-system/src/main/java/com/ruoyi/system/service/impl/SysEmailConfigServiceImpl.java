package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysEmailConfigMapper;
import com.ruoyi.system.domain.SysEmailConfig;
import com.ruoyi.system.service.ISysEmailConfigService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-26
 */
@Service
public class SysEmailConfigServiceImpl implements ISysEmailConfigService 
{
    @Autowired
    private SysEmailConfigMapper sysEmailConfigMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public SysEmailConfig selectSysEmailConfigById(Long id)
    {
        return sysEmailConfigMapper.selectSysEmailConfigById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysEmailConfig 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SysEmailConfig> selectSysEmailConfigList(SysEmailConfig sysEmailConfig)
    {
        return sysEmailConfigMapper.selectSysEmailConfigList(sysEmailConfig);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysEmailConfig 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertSysEmailConfig(SysEmailConfig sysEmailConfig)
    {
        return sysEmailConfigMapper.insertSysEmailConfig(sysEmailConfig);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysEmailConfig 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSysEmailConfig(SysEmailConfig sysEmailConfig)
    {
        return sysEmailConfigMapper.updateSysEmailConfig(sysEmailConfig);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteSysEmailConfigByIds(Long[] ids)
    {
        return sysEmailConfigMapper.deleteSysEmailConfigByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteSysEmailConfigById(Long id)
    {
        return sysEmailConfigMapper.deleteSysEmailConfigById(id);
    }
}
