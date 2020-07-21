package com.ruoyi.maintenance.mapper;

import com.ruoyi.maintenance.domain.MntServer;
import com.ruoyi.maintenance.domain.ServerDeploy;
import com.ruoyi.maintenance.domain.ServerDeployDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 服务器管理Mapper接口
 * 
 * @author ruoyi
 * @date 2020-07-16
 */
public interface MntServerMapper 
{
    /**
     * 查询服务器管理
     * 
     * @param serverId 服务器管理ID
     * @return 服务器管理
     */
    public MntServer selectMntServerById(Long serverId);

    /**
     * 查询服务器管理列表
     * 
     * @param mntServer 服务器管理
     * @return 服务器管理集合
     */
    public List<MntServer> selectMntServerList(MntServer mntServer);

    /**
     * 新增服务器管理
     * 
     * @param mntServer 服务器管理
     * @return 结果
     */
    public int insertMntServer(MntServer mntServer);

    /**
     * 修改服务器管理
     * 
     * @param mntServer 服务器管理
     * @return 结果
     */
    public int updateMntServer(MntServer mntServer);

    /**
     * 删除服务器管理
     * 
     * @param serverId 服务器管理ID
     * @return 结果
     */
    public int deleteMntServerById(Long serverId);

    /**
     * 批量删除服务器管理
     * 
     * @param serverIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMntServerByIds(Long[] serverIds);

    List<MntServer> queryAll();

    ServerDeployDto findByIp(@Param("ip") String ip);

    ServerDeploy selectServerDeploybyId(@Param("serverId") Long serverId);
}
