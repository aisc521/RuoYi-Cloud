package com.ruoyi.maintenance.service;

import com.ruoyi.maintenance.domain.MntDeployHistory;

import java.util.List;


/**
 * 部署历史管理Service接口
 * 
 * @author ruoyi
 * @date 2020-07-21
 */
public interface IMntDeployHistoryService 
{
    /**
     * 查询部署历史管理
     * 
     * @param historyId 部署历史管理ID
     * @return 部署历史管理
     */
    public MntDeployHistory selectMntDeployHistoryById(String historyId);

    /**
     * 查询部署历史管理列表
     * 
     * @param mntDeployHistory 部署历史管理
     * @return 部署历史管理集合
     */
    public List<MntDeployHistory> selectMntDeployHistoryList(MntDeployHistory mntDeployHistory);

    /**
     * 新增部署历史管理
     * 
     * @param mntDeployHistory 部署历史管理
     * @return 结果
     */
    public int insertMntDeployHistory(MntDeployHistory mntDeployHistory);

    /**
     * 修改部署历史管理
     * 
     * @param mntDeployHistory 部署历史管理
     * @return 结果
     */
    public int updateMntDeployHistory(MntDeployHistory mntDeployHistory);

    /**
     * 批量删除部署历史管理
     * 
     * @param historyIds 需要删除的部署历史管理ID
     * @return 结果
     */
    public int deleteMntDeployHistoryByIds(String[] historyIds);

    /**
     * 删除部署历史管理信息
     * 
     * @param historyId 部署历史管理ID
     * @return 结果
     */
    public int deleteMntDeployHistoryById(String historyId);
}
