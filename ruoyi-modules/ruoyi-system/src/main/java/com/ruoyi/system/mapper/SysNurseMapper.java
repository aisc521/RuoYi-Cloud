package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysNurse;
import com.ruoyi.system.domain.vo.SysNurseVo;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-22
 */
public interface SysNurseMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public SysNurse selectSysNurseById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysNurse 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysNurseVo> selectSysNurseList(SysNurse sysNurse);

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysNurse 【请填写功能名称】
     * @return 结果
     */
    public int insertSysNurse(SysNurse sysNurse);

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysNurse 【请填写功能名称】
     * @return 结果
     */
    public int updateSysNurse(SysNurse sysNurse);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteSysNurseById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysNurseByIds(Long[] ids);

    SysNurse selectSysNurseByIdFroLastInfo(SysNurse sysNurse);

    SysNurse checkAdd(SysNurse sysNurse);

    List<SysNurseVo> selectSysNurseListLast(SysNurse sysNurse);
}
