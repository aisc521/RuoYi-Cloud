package com.ruoyi.maintenance.service.impl;

import java.util.List;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.shell.ExecuteShellUtil;
import com.ruoyi.maintenance.domain.MntServer;
import com.ruoyi.maintenance.domain.ServerDeploy;
import com.ruoyi.maintenance.mapper.MntServerMapper;
import com.ruoyi.maintenance.service.IMntServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 服务器管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-16
 */
@Service
public class MntServerServiceImpl implements IMntServerService
{
    @Autowired
    private MntServerMapper mntServerMapper;

    /**
     * 查询服务器管理
     * 
     * @param serverId 服务器管理ID
     * @return 服务器管理
     */
    @Override
    public MntServer selectMntServerById(Long serverId)
    {
        return mntServerMapper.selectMntServerById(serverId);
    }

    /**
     * 查询服务器管理列表
     * 
     * @param mntServer 服务器管理
     * @return 服务器管理
     */
    @Override
    public List<MntServer> selectMntServerList(MntServer mntServer)
    {
        return mntServerMapper.selectMntServerList(mntServer);
    }

    /**
     * 新增服务器管理
     * 
     * @param mntServer 服务器管理
     * @return 结果
     */
    @Override
    public int insertMntServer(MntServer mntServer)
    {
        mntServer.setCreateTime(DateUtils.getNowDate());
        return mntServerMapper.insertMntServer(mntServer);
    }

    /**
     * 修改服务器管理
     * 
     * @param mntServer 服务器管理
     * @return 结果
     */
    @Override
    public int updateMntServer(MntServer mntServer)
    {
        mntServer.setUpdateTime(DateUtils.getNowDate());
        return mntServerMapper.updateMntServer(mntServer);
    }

    /**
     * 批量删除服务器管理
     * 
     * @param serverIds 需要删除的服务器管理ID
     * @return 结果
     */
    @Override
    public int deleteMntServerByIds(Long[] serverIds)
    {
        return mntServerMapper.deleteMntServerByIds(serverIds);
    }

    /**
     * 删除服务器管理信息
     * 
     * @param serverId 服务器管理ID
     * @return 结果
     */
    @Override
    public int deleteMntServerById(Long serverId)
    {
        return mntServerMapper.deleteMntServerById(serverId);
    }

    @Override
    public Boolean testConnect(MntServer resources) {
        ExecuteShellUtil executeShellUtil = null;
        try {
            executeShellUtil = new ExecuteShellUtil(resources.getIp(), resources.getAccount(), resources.getPassword(), Math.toIntExact(resources.getPort()));
            return executeShellUtil.execute("ls")==0;
        } catch (Exception e) {
            return false;
        }finally {
            if (executeShellUtil != null) {
                executeShellUtil.close();
            }
        }
    }

    @Override
    public List<MntServer> queryAll() {
        return mntServerMapper.queryAll();
    }

    @Override
    public ServerDeploy selectServerDeploybyId(Long serverId) {
        return mntServerMapper.selectServerDeploybyId(serverId);
    }
}
