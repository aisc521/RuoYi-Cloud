package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysEmailConfig;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-26
 */
public interface SysEmailConfigMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public SysEmailConfig selectSysEmailConfigById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysEmailConfig 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysEmailConfig> selectSysEmailConfigList(SysEmailConfig sysEmailConfig);

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysEmailConfig 【请填写功能名称】
     * @return 结果
     */
    public int insertSysEmailConfig(SysEmailConfig sysEmailConfig);

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysEmailConfig 【请填写功能名称】
     * @return 结果
     */
    public int updateSysEmailConfig(SysEmailConfig sysEmailConfig);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteSysEmailConfigById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysEmailConfigByIds(Long[] ids);

}
