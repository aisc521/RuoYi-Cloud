package com.ruoyi.maintenance.service;

import com.ruoyi.maintenance.domain.MntDatabase;
import net.sf.jsqlparser.schema.Database;

import java.util.List;


/**
 * 数据库管理Service接口
 * 
 * @author ruoyi
 * @date 2020-07-20
 */
public interface IMntDatabaseService 
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
     * 批量删除数据库管理
     * 
     * @param dbIds 需要删除的数据库管理ID
     * @return 结果
     */
    public int deleteMntDatabaseByIds(String[] dbIds);

    /**
     * 删除数据库管理信息
     * 
     * @param dbId 数据库管理ID
     * @return 结果
     */
    public int deleteMntDatabaseById(String dbId);

    MntDatabase findById(String id);

    Boolean testConnection(MntDatabase resources);
}
