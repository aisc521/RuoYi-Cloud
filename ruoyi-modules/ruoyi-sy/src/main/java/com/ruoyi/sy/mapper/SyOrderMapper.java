package com.ruoyi.sy.mapper;

import com.ruoyi.sy.domain.SyOrder;

import java.util.List;


/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2020-07-21
 */
public interface SyOrderMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public SyOrder selectSyOrderById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param syOrder 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SyOrder> selectSyOrderList(SyOrder syOrder);

    /**
     * 新增【请填写功能名称】
     * 
     * @param syOrder 【请填写功能名称】
     * @return 结果
     */
    public int insertSyOrder(SyOrder syOrder);

    /**
     * 修改【请填写功能名称】
     * 
     * @param syOrder 【请填写功能名称】
     * @return 结果
     */
    public int updateSyOrder(SyOrder syOrder);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteSyOrderById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSyOrderByIds(Long[] ids);
}
