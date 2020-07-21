package com.ruoyi.maintenance.mapper;

import com.ruoyi.maintenance.domain.MntDatabase;

import java.util.List;


/**
 * 数据库管理Mapper接口
 * 
 * @author ruoyi
 * @date 2020-07-20
 */
public interface MntDatabaseMapper 
{
    /**
     * 查询数据库管理
     * 
     * @param dbId 数据库管理ID
     * @return 数据库管理
     */
    public MntDatabase selectMntDatabaseById(String dbId);

    /**
     * 查询数据库管理列表
     * 
     * @param mntDatabase 数据库管理
     * @return 数据库管理集合
     */
    public List<MntDatabase> selectMntDatabaseList(MntDatabase mntDatabase);

    /**
     * 新增数据库管理
     * 
     * @param mntDatabase 数据库管理
     * @return 结果
     */
    public int insertMntDatabase(MntDatabase mntDatabase);

    /**
     * 修改数据库管理
     * 
     * @param mntDatabase 数据库管理
     * @return 结果
     */
    public int updateMntDatabase(MntDatabase mntDatabase);

    /**
     * 删除数据库管理
     * 
     * @param dbId 数据库管理ID
     * @return 结果
     */
    public int deleteMntDatabaseById(String dbId);

    /**
     * 批量删除数据库管理
     * 
     * @param dbIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMntDatabaseByIds(String[] dbIds);
}
