package com.ruoyi.maintenance.mapper;

import com.ruoyi.maintenance.domain.MntDeploy;
import com.ruoyi.maintenance.domain.MntDeployDto;

import java.util.List;


/**
 * 部署管理Mapper接口
 * 
 * @author ruoyi
 * @date 2020-07-17
 */
public interface MntDeployMapper 
{
    /**
     * 查询部署管理
     * 
     * @param deployId 部署管理ID
     * @return 部署管理
     */
    public MntDeploy selectMntDeployById(Long deployId);

    /**
     * 查询部署管理列表
     * 
     * @param mntDeploy 部署管理
     * @return 部署管理集合
     */
    public List<MntDeployDto> selectMntDeployList(MntDeploy mntDeploy);

    /**
     * 新增部署管理
     * 
     * @param mntDeploy 部署管理
     * @return 结果
     */
    public int insertMntDeploy(MntDeploy mntDeploy);

    /**
     * 修改部署管理
     * 
     * @param mntDeploy 部署管理
     * @return 结果
     */
    public int updateMntDeploy(MntDeploy mntDeploy);

    /**
     * 删除部署管理
     * 
     * @param deployId 部署管理ID
     * @return 结果
     */
    public int deleteMntDeployById(Long deployId);

    /**
     * 批量删除部署管理
     * 
     * @param deployIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMntDeployByIds(Long[] deployIds);
}
