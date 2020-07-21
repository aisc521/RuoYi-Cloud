package com.ruoyi.maintenance.service.impl;

import java.util.List;

import com.ruoyi.maintenance.domain.MntDeployServer;
import com.ruoyi.maintenance.mapper.MntDeployServerMapper;
import com.ruoyi.maintenance.service.IMntDeployServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 应用与服务器关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-20
 */
@Service
public class MntDeployServerServiceImpl implements IMntDeployServerService
{
    @Autowired
    private MntDeployServerMapper mntDeployServerMapper;

    /**
     * 查询应用与服务器关联
     * 
     * @param deployId 应用与服务器关联ID
     * @return 应用与服务器关联
     */
    @Override
    public MntDeployServer selectMntDeployServerById(Long deployId)
    {
        return mntDeployServerMapper.selectMntDeployServerById(deployId);
    }

    /**
     * 查询应用与服务器关联列表
     * 
     * @param mntDeployServer 应用与服务器关联
     * @return 应用与服务器关联
     */
    @Override
    public List<MntDeployServer> selectMntDeployServerList(MntDeployServer mntDeployServer)
    {
        return mntDeployServerMapper.selectMntDeployServerList(mntDeployServer);
    }

    /**
     * 新增应用与服务器关联
     * 
     * @param mntDeployServer 应用与服务器关联
     * @return 结果
     */
    @Override
    public int insertMntDeployServer(MntDeployServer mntDeployServer)
    {
        return mntDeployServerMapper.insertMntDeployServer(mntDeployServer);
    }

    /**
     * 修改应用与服务器关联
     * 
     * @param mntDeployServer 应用与服务器关联
     * @return 结果
     */
    @Override
    public int updateMntDeployServer(MntDeployServer mntDeployServer)
    {
        return mntDeployServerMapper.updateMntDeployServer(mntDeployServer);
    }

    /**
     * 批量删除应用与服务器关联
     * 
     * @param deployIds 需要删除的应用与服务器关联ID
     * @return 结果
     */
    @Override
    public int deleteMntDeployServerByIds(Long[] deployIds)
    {
        return mntDeployServerMapper.deleteMntDeployServerByIds(deployIds);
    }

    /**
     * 删除应用与服务器关联信息
     * 
     * @param deployId 应用与服务器关联ID
     * @return 结果
     */
    @Override
    public int deleteMntDeployServerById(Long deployId)
    {
        return mntDeployServerMapper.deleteMntDeployServerById(deployId);
    }
}
