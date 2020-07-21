package com.ruoyi.maintenance.service.impl;

import java.util.List;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.maintenance.domain.App;
import com.ruoyi.maintenance.domain.MntApp;
import com.ruoyi.maintenance.mapper.MntAppMapper;
import com.ruoyi.maintenance.service.IMntAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 应用管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-17
 */
@Service
public class MntAppServiceImpl implements IMntAppService
{
    @Autowired
    private MntAppMapper mntAppMapper;

    /**
     * 查询应用管理
     * 
     * @param appId 应用管理ID
     * @return 应用管理
     */
    @Override
    public MntApp selectMntAppById(Long appId)
    {
        return mntAppMapper.selectMntAppById(appId);
    }

    /**
     * 查询应用管理列表
     * 
     * @param mntApp 应用管理
     * @return 应用管理
     */
    @Override
    public List<MntApp> selectMntAppList(MntApp mntApp)
    {
        return mntAppMapper.selectMntAppList(mntApp);
    }

    /**
     * 新增应用管理
     * 
     * @param mntApp 应用管理
     * @return 结果
     */
    @Override
    public int insertMntApp(MntApp mntApp)
    {
        mntApp.setCreateTime(DateUtils.getNowDate());
        return mntAppMapper.insertMntApp(mntApp);
    }

    /**
     * 修改应用管理
     * 
     * @param mntApp 应用管理
     * @return 结果
     */
    @Override
    public int updateMntApp(MntApp mntApp)
    {
        mntApp.setUpdateTime(DateUtils.getNowDate());
        return mntAppMapper.updateMntApp(mntApp);
    }

    /**
     * 批量删除应用管理
     * 
     * @param appIds 需要删除的应用管理ID
     * @return 结果
     */
    @Override
    public int deleteMntAppByIds(Long[] appIds)
    {
        return mntAppMapper.deleteMntAppByIds(appIds);
    }

    /**
     * 删除应用管理信息
     * 
     * @param appId 应用管理ID
     * @return 结果
     */
    @Override
    public int deleteMntAppById(Long appId)
    {
        return mntAppMapper.deleteMntAppById(appId);
    }

    @Override
    public List<MntApp> queryAll() {
        return mntAppMapper.queryAll();
    }

    @Override
    public App selectMntAppByIdForApp(Long appId) {
        return mntAppMapper.selectMntAppByIdForApp(appId);
    }
}
