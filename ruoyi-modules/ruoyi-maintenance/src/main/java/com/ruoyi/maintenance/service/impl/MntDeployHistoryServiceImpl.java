package com.ruoyi.maintenance.service.impl;

import java.util.List;

import com.ruoyi.maintenance.domain.MntDeployHistory;
import com.ruoyi.maintenance.mapper.MntDeployHistoryMapper;
import com.ruoyi.maintenance.service.IMntDeployHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 部署历史管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-21
 */
@Service
public class MntDeployHistoryServiceImpl implements IMntDeployHistoryService
{
    @Autowired
    private MntDeployHistoryMapper mntDeployHistoryMapper;

    /**
     * 查询部署历史管理
     * 
     * @param historyId 部署历史管理ID
     * @return 部署历史管理
     */
    @Override
    public MntDeployHistory selectMntDeployHistoryById(String historyId)
    {
        return mntDeployHistoryMapper.selectMntDeployHistoryById(historyId);
    }

    /**
     * 查询部署历史管理列表
     * 
     * @param mntDeployHistory 部署历史管理
     * @return 部署历史管理
     */
    @Override
    public List<MntDeployHistory> selectMntDeployHistoryList(MntDeployHistory mntDeployHistory)
    {
        return mntDeployHistoryMapper.selectMntDeployHistoryList(mntDeployHistory);
    }

    /**
     * 新增部署历史管理
     * 
     * @param mntDeployHistory 部署历史管理
     * @return 结果
     */
    @Override
    public int insertMntDeployHistory(MntDeployHistory mntDeployHistory)
    {
        return mntDeployHistoryMapper.insertMntDeployHistory(mntDeployHistory);
    }

    /**
     * 修改部署历史管理
     * 
     * @param mntDeployHistory 部署历史管理
     * @return 结果
     */
    @Override
    public int updateMntDeployHistory(MntDeployHistory mntDeployHistory)
    {
        return mntDeployHistoryMapper.updateMntDeployHistory(mntDeployHistory);
    }

    /**
     * 批量删除部署历史管理
     * 
     * @param historyIds 需要删除的部署历史管理ID
     * @return 结果
     */
    @Override
    public int deleteMntDeployHistoryByIds(String[] historyIds)
    {
        return mntDeployHistoryMapper.deleteMntDeployHistoryByIds(historyIds);
    }

    /**
     * 删除部署历史管理信息
     * 
     * @param historyId 部署历史管理ID
     * @return 结果
     */
    @Override
    public int deleteMntDeployHistoryById(String historyId)
    {
        return mntDeployHistoryMapper.deleteMntDeployHistoryById(historyId);
    }
}
