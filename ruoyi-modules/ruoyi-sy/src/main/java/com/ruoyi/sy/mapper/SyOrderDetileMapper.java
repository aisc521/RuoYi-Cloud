package com.ruoyi.sy.mapper;

import com.ruoyi.sy.domain.SyOrderDetile;

import java.util.List;


/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2020-07-21
 */
public interface SyOrderDetileMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public SyOrderDetile selectSyOrderDetileById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param syOrderDetile 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SyOrderDetile> selectSyOrderDetileList(SyOrderDetile syOrderDetile);

    /**
     * 新增【请填写功能名称】
     * 
     * @param syOrderDetile 【请填写功能名称】
     * @return 结果
     */
    public int insertSyOrderDetile(SyOrderDetile syOrderDetile);

    /**
     * 修改【请填写功能名称】
     * 
     * @param syOrderDetile 【请填写功能名称】
     * @return 结果
     */
    public int updateSyOrderDetile(SyOrderDetile syOrderDetile);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteSyOrderDetileById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSyOrderDetileByIds(Long[] ids);
}
