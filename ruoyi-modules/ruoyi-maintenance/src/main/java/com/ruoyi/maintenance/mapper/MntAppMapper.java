package com.ruoyi.maintenance.mapper;

import com.ruoyi.maintenance.domain.App;
import com.ruoyi.maintenance.domain.MntApp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 应用管理Mapper接口
 * 
 * @author ruoyi
 * @date 2020-07-17
 */
public interface MntAppMapper 
{
    /**
     * 查询应用管理
     * 
     * @param appId 应用管理ID
     * @return 应用管理
     */
    public MntApp selectMntAppById(Long appId);

    /**
     * 查询应用管理列表
     * 
     * @param mntApp 应用管理
     * @return 应用管理集合
     */
    public List<MntApp> selectMntAppList(MntApp mntApp);

    /**
     * 新增应用管理
     * 
     * @param mntApp 应用管理
     * @return 结果
     */
    public int insertMntApp(MntApp mntApp);

    /**
     * 修改应用管理
     * 
     * @param mntApp 应用管理
     * @return 结果
     */
    public int updateMntApp(MntApp mntApp);

    /**
     * 删除应用管理
     * 
     * @param appId 应用管理ID
     * @return 结果
     */
    public int deleteMntAppById(Long appId);

    /**
     * 批量删除应用管理
     * 
     * @param appIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMntAppByIds(Long[] appIds);

    List<MntApp> queryAll();

    App selectMntAppByIdForApp(@Param("appId") Long appId);
}
