package com.ruoyi.maintenance.service.impl;

import java.util.List;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.SqlUtils;
import com.ruoyi.maintenance.domain.MntDatabase;
import com.ruoyi.maintenance.mapper.MntDatabaseMapper;
import com.ruoyi.maintenance.service.IMntDatabaseService;
import net.sf.jsqlparser.schema.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 数据库管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-20
 */
@Service
public class MntDatabaseServiceImpl implements IMntDatabaseService
{
    @Autowired
    private MntDatabaseMapper mntDatabaseMapper;

    /**
     * 查询数据库管理
     * 
     * @param dbId 数据库管理ID
     * @return 数据库管理
     */
    @Override
    public MntDatabase selectMntDatabaseById(String dbId)
    {
        return mntDatabaseMapper.selectMntDatabaseById(dbId);
    }

    /**
     * 查询数据库管理列表
     * 
     * @param mntDatabase 数据库管理
     * @return 数据库管理
     */
    @Override
    public List<MntDatabase> selectMntDatabaseList(MntDatabase mntDatabase)
    {
        return mntDatabaseMapper.selectMntDatabaseList(mntDatabase);
    }

    /**
     * 新增数据库管理
     * 
     * @param mntDatabase 数据库管理
     * @return 结果
     */
    @Override
    public int insertMntDatabase(MntDatabase mntDatabase)
    {
        mntDatabase.setCreateTime(DateUtils.getNowDate());
        return mntDatabaseMapper.insertMntDatabase(mntDatabase);
    }

    /**
     * 修改数据库管理
     * 
     * @param mntDatabase 数据库管理
     * @return 结果
     */
    @Override
    public int updateMntDatabase(MntDatabase mntDatabase)
    {
        mntDatabase.setUpdateTime(DateUtils.getNowDate());
        return mntDatabaseMapper.updateMntDatabase(mntDatabase);
    }

    /**
     * 批量删除数据库管理
     * 
     * @param dbIds 需要删除的数据库管理ID
     * @return 结果
     */
    @Override
    public int deleteMntDatabaseByIds(String[] dbIds)
    {
        return mntDatabaseMapper.deleteMntDatabaseByIds(dbIds);
    }

    /**
     * 删除数据库管理信息
     * 
     * @param dbId 数据库管理ID
     * @return 结果
     */
    @Override
    public int deleteMntDatabaseById(String dbId)
    {
        return mntDatabaseMapper.deleteMntDatabaseById(dbId);
    }

    @Override
    public MntDatabase findById(String id) {
        return mntDatabaseMapper.selectMntDatabaseById(id);
    }

    @Override
    public Boolean testConnection(MntDatabase resources) {
        try {
            return SqlUtils.testConnection(resources.getJdbcUrl(), resources.getUserName(), resources.getPwd());
        } catch (Exception e) {
            return false;
        }
    }
}
