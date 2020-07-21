package com.ruoyi.maintenance.service;

import com.ruoyi.maintenance.domain.MntDeployServer;

import java.util.List;


/**
 * 应用与服务器关联Service接口
 * 
 * @author ruoyi
 * @date 2020-07-20
 */
public interface IMntDeployServerService 
{
    /**
     * 查询应用与服务器关联
     * 
     * @param deployId 应用与服务器关联ID
     * @return 应用与服务器关联
     */
    public MntDeployServer selectMntDeployServerById(Long deployId);

    /**
     * 查询应用与服务器关联列表
     * 
     * @param mntDeployServer 应用与服务器关联
     * @return 应用与服务器关联集合
     */
    public List<MntDeployServer> selectMntDeployServerList(MntDeployServer mntDeployServer);

    /**
     * 新增应用与服务器关联
     * 
     * @param mntDeployServer 应用与服务器关联
     * @return 结果
     */
    public int insertMntDeployServer(MntDeployServer mntDeployServer);

    /**
     * 修改应用与服务器关联
     * 
     * @param mntDeployServer 应用与服务器关联
     * @return 结果
     */
    public int updateMntDeployServer(MntDeployServer mntDeployServer);

    /**
     * 批量删除应用与服务器关联
     * 
     * @param deployIds 需要删除的应用与服务器关联ID
     * @return 结果
     */
    public int deleteMntDeployServerByIds(Long[] deployIds);

    /**
     * 删除应用与服务器关联信息
     * 
     * @param deployId 应用与服务器关联ID
     * @return 结果
     */
    public int deleteMntDeployServerById(Long deployId);
}
