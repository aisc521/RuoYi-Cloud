package com.ruoyi.maintenance.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.ruoyi.common.core.utils.BadRequestException;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.ScpClientUtil;
import com.ruoyi.common.core.utils.shell.ExecuteShellUtil;
import com.ruoyi.common.datascope.service.AwaitUserService;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.maintenance.domain.*;
import com.ruoyi.maintenance.mapper.*;
import com.ruoyi.maintenance.service.IMntDeployService;
import com.ruoyi.maintenance.utils.websocket.MsgType;
import com.ruoyi.maintenance.utils.websocket.SocketMsg;
import com.ruoyi.maintenance.utils.websocket.WebSocketServer;
import com.ruoyi.system.api.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;



/**
 * 部署管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-17
 */
@Service
@Slf4j
public class MntDeployServiceImpl implements IMntDeployService

{
    private final String FILE_SEPARATOR = "/";
    @Autowired
    private MntDeployMapper mntDeployMapper;
    @Autowired
    private AwaitUserService awaitUserService;
    @Autowired
    private MntDeployServerMapper mntDeployServerMapper;
    @Autowired
    private MntServerMapper mntServerMapper;
    @Autowired
    private MntAppMapper mntAppMapper;
    @Autowired
    private MntDeployHistoryMapper mntDeployHistoryMapper;
    /**
     * 循环次数
     */
    private final Integer count = 30;
    /**
     * 查询部署管理
     * 
     * @param deployId 部署管理ID
     * @return 部署管理
     */
    @Override
    public MntDeploy selectMntDeployById(Long deployId)
    {
        return mntDeployMapper.selectMntDeployById(deployId);
    }

    /**
     * 查询部署管理列表
     * 
     * @param mntDeploy 部署管理
     * @return 部署管理
     */
    @Override
    public List<MntDeployDto> selectMntDeployList(MntDeploy mntDeploy)
    {



        return mntDeployMapper.selectMntDeployList(mntDeploy);
    }

    /**
     * 新增部署管理
     * 
     * @param mntDeploy 部署管理
     * @return 结果
     */
    @Override
    public int insertMntDeploy(MntDeploy mntDeploy)
    {
        mntDeploy.setCreateTime(DateUtils.getNowDate());
        return mntDeployMapper.insertMntDeploy(mntDeploy);
    }

    /**
     * 修改部署管理
     * 
     * @param mntDeploy 部署管理
     * @return 结果
     */
    @Override
    public int updateMntDeploy(MntDeploy mntDeploy)
    {
        mntDeploy.setUpdateTime(DateUtils.getNowDate());
        return mntDeployMapper.updateMntDeploy(mntDeploy);
    }

    /**
     * 批量删除部署管理
     * 
     * @param deployIds 需要删除的部署管理ID
     * @return 结果
     */
    @Override
    public int deleteMntDeployByIds(Long[] deployIds)
    {
        return mntDeployMapper.deleteMntDeployByIds(deployIds);
    }

    /**
     * 删除部署管理信息
     * 
     * @param deployId 部署管理ID
     * @return 结果
     */
    @Override
    public int deleteMntDeployById(Long deployId)
    {
        return mntDeployMapper.deleteMntDeployById(deployId);
    }

    @Override
    public int create(Deploy mntDeploy) {
        MntDeploy mntDeploy1 = new MntDeploy();
        mntDeploy1.setAppId(mntDeploy.getApp().getId());
        UserInfo userInfo = awaitUserService.info();
        mntDeploy1.setCreateBy(userInfo.getSysUser().getCreateBy());
        mntDeploy1.setUpdateBy(userInfo.getSysUser().getCreateBy());
        mntDeploy1.setCreateTime(new Date());
        mntDeploy1.setUpdateTime(new Date());
        mntDeployMapper.insertMntDeploy(mntDeploy1);
        Long id = mntDeploy1.getDeployId();
        Set<ServerDeploy> deploys = mntDeploy.getDeploys();

        Iterator it = deploys.iterator();
        while (it.hasNext()) {
            ServerDeploy str = (ServerDeploy) it.next();
            MntDeployServer mntDeployServer = new MntDeployServer();
            mntDeployServer.setDeployId(id);
            mntDeployServer.setServerId(str.getId());
            mntDeployServerMapper.insertMntDeployServer(mntDeployServer);

        }
        return 1;
    }

    @Override
    public String serverStatus(Deploy resources) {
        Set<ServerDeploy> serverDeploys = resources.getDeploys();
        App app = resources.getApp();
        for (ServerDeploy serverDeploy : serverDeploys) {
            StringBuilder sb = new StringBuilder();
            ExecuteShellUtil executeShellUtil = getExecuteShellUtil(serverDeploy.getIp());
            sb.append("服务器:").append(serverDeploy.getName()).append("<br>应用:").append(app.getName());
            boolean result = checkIsRunningStatus(app.getPort(), executeShellUtil);
            if (result) {
                sb.append("<br>正在运行");
                sendMsg(sb.toString(), MsgType.INFO);
            } else {
                sb.append("<br>已停止!");
                sendMsg(sb.toString(), MsgType.ERROR);
            }
            log.info(sb.toString());
            executeShellUtil.close();
            return sb.toString();
        }
        return "执行完毕";
    }

    @Override
    public String startServer(Deploy resources) {
        Set<ServerDeploy> deploys = resources.getDeploys();
        App app = resources.getApp();
        for (ServerDeploy deploy : deploys) {
            StringBuilder sb = new StringBuilder();
            ExecuteShellUtil executeShellUtil = getExecuteShellUtil(deploy.getIp());
            //为了防止重复启动，这里先停止应用
            stopApp(app.getPort(), executeShellUtil);
            sb.append("服务器:").append(deploy.getName()).append("<br>应用:").append(app.getName());
            sendMsg("下发启动命令", MsgType.INFO);
            executeShellUtil.execute(app.getStartScript());
            sleep(3);
            sendMsg("应用启动中，请耐心等待启动结果，或者稍后手动查看运行状态", MsgType.INFO);
            int i  = 0;
            boolean result = false;
            // 由于启动应用需要时间，所以需要循环获取状态，如果超过30次，则认为是启动失败
            while (i++ < count){
                result = checkIsRunningStatus(app.getPort(), executeShellUtil);
                if(result){
                    break;
                }
                // 休眠6秒
                sleep(6);
            }
            sendResultMsg(result, sb);
            log.info(sb.toString());
            executeShellUtil.close();
            return sb.toString();
        }
        return "执行完毕";
    }


    /**
     * 停止服务
     * @param resources /
     * @return /
     */
    @Override
    public String stopServer(Deploy resources) {
        Set<ServerDeploy> deploys = resources.getDeploys();
        App app = resources.getApp();
        for (ServerDeploy deploy : deploys) {
            StringBuilder sb = new StringBuilder();
            ExecuteShellUtil executeShellUtil = getExecuteShellUtil(deploy.getIp());
            sb.append("服务器:").append(deploy.getName()).append("<br>应用:").append(app.getName());
            sendMsg("下发停止命令", MsgType.INFO);
            //停止应用
            stopApp(app.getPort(), executeShellUtil);
            sleep(1);
            boolean result = checkIsRunningStatus(app.getPort(), executeShellUtil);
            if (result) {
                sb.append("<br>关闭失败!");
                sendMsg(sb.toString(), MsgType.ERROR);
            } else {
                sb.append("<br>关闭成功!");
                sendMsg(sb.toString(), MsgType.INFO);
            }
            log.info(sb.toString());
            executeShellUtil.close();
            return sb.toString();
        }
        return "执行完毕";
    }

    @Override
    public void deploy(String fileSavePath, Long id) {
        deployApp(fileSavePath, id);
    }

    /**
     * @param fileSavePath 本机路径
     * @param id ID
     */
    private void deployApp(String fileSavePath, Long id) {
        Deploy deploy = new Deploy();
        deploy.setId(id);
        //根据id 拼装 Deploy信息
        MntDeploy mntDeploy = mntDeployMapper.selectMntDeployById(id);
        App app = mntAppMapper.selectMntAppByIdForApp(mntDeploy.getAppId());
        deploy.setApp(app);
        MntDeployServer mntDeployServer = new MntDeployServer();
        mntDeployServer.setDeployId(id);
        List<MntDeployServer> mntDeployServerList = mntDeployServerMapper.selectMntDeployServerList(mntDeployServer);
        if(mntDeployServerList.size() > 0){
            Set<ServerDeploy> deploys = new HashSet<ServerDeploy>();
            for (int i = 0; i < mntDeployServerList.size(); i++) {
                ServerDeploy serverDeploy = mntServerMapper.selectServerDeploybyId(mntDeployServerList.get(i).getServerId());
                deploys.add(serverDeploy);
            }
            deploy.setDeploys(deploys);
        }


        if (deploy == null) {
            sendMsg("部署信息不存在", MsgType.ERROR);
            throw new BadRequestException("部署信息不存在");
        }
        App app1 = deploy.getApp();
        if (app1 == null) {
            sendMsg("包对应应用信息不存在", MsgType.ERROR);
            throw new BadRequestException("包对应应用信息不存在");
        }
        int port = app1.getPort();
        //这个是服务器部署路径
        String uploadPath = app1.getUploadPath();
        StringBuilder sb = new StringBuilder();
        String msg;
        Set<ServerDeploy> deploys = deploy.getDeploys();
        for (ServerDeploy deployDTO : deploys) {
            String ip = deployDTO.getIp();
            ExecuteShellUtil executeShellUtil = getExecuteShellUtil(ip);
            //判断是否第一次部署
            boolean flag = checkFile(executeShellUtil, app1);
            //第一步要确认服务器上有这个目录
            executeShellUtil.execute("mkdir -p " + app1.getUploadPath());
            executeShellUtil.execute("mkdir -p " + app1.getBackupPath());
            executeShellUtil.execute("mkdir -p " + app1.getDeployPath());
            //上传文件
            msg = String.format("登陆到服务器:%s", ip);
            ScpClientUtil scpClientUtil = getScpClientUtil(ip);
            log.info(msg);
            sendMsg(msg, MsgType.INFO);
            msg = String.format("上传文件到服务器:%s<br>目录:%s下，请稍等...", ip, uploadPath);
            sendMsg(msg, MsgType.INFO);
            scpClientUtil.putFile(fileSavePath, uploadPath);
            if (flag) {
                sendMsg("停止原来应用", MsgType.INFO);
                //停止应用
                stopApp(port, executeShellUtil);
                sendMsg("备份原来应用", MsgType.INFO);
                //备份应用
                backupApp(executeShellUtil, ip, app.getDeployPath()+FILE_SEPARATOR, app1.getName(), app1.getBackupPath()+FILE_SEPARATOR, id);
            }
            sendMsg("部署应用", MsgType.INFO);
            //部署文件,并启动应用
            String deployScript = app1.getDeployScript();
            executeShellUtil.execute(deployScript);
            sleep(3);
            sendMsg("应用部署中，请耐心等待部署结果，或者稍后手动查看部署状态", MsgType.INFO);
            int i  = 0;
            boolean result = false;
            // 由于启动应用需要时间，所以需要循环获取状态，如果超过30次，则认为是启动失败
            while (i++ < count){
                result = checkIsRunningStatus(port, executeShellUtil);
                if(result){
                    break;
                }
                // 休眠6秒
                sleep(6);
            }
            sb.append("服务器:").append(deployDTO.getName()).append("<br>应用:").append(app1.getName());
            sendResultMsg(result, sb);
            executeShellUtil.close();
        }
    }

    private void backupApp(ExecuteShellUtil executeShellUtil, String ip, String fileSavePath, String appName, String backupPath, Long id) {
        String deployDate = DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN);
        StringBuilder sb = new StringBuilder();
        backupPath += appName + FILE_SEPARATOR + deployDate + "\n";
        sb.append("mkdir -p ").append(backupPath);
        sb.append("mv -f ").append(fileSavePath);
        sb.append(appName).append(" ").append(backupPath);
        log.info("备份应用脚本:" + sb.toString());
        executeShellUtil.execute(sb.toString());
        //还原信息入库
        MntDeployHistory deployHistory = new MntDeployHistory();
        deployHistory.setAppName(appName);
        deployHistory.setDeployUser("admin");
        deployHistory.setIp(ip);
        deployHistory.setDeployId(id);
        deployHistory.setHistoryId(IdUtil.simpleUUID());
        deployHistory.setDeployDate(new Date());
        mntDeployHistoryMapper.create(deployHistory);
    }

    private ScpClientUtil getScpClientUtil(String ip) {
        ServerDeployDto serverDeployDTO = mntServerMapper.findByIp(ip);
        if (serverDeployDTO == null) {
            sendMsg("IP对应服务器信息不存在：" + ip, MsgType.ERROR);
            throw new BadRequestException("IP对应服务器信息不存在：" + ip);
        }
        return ScpClientUtil.getInstance(ip, serverDeployDTO.getPort(), serverDeployDTO.getAccount(), serverDeployDTO.getPassword());
    }
    private boolean checkFile(ExecuteShellUtil executeShellUtil, App appDTO) {
        String result = executeShellUtil.executeForResult("find " + appDTO.getDeployPath() + " -name " + appDTO.getName());
        return result.indexOf(appDTO.getName())>0;
    }
    private void sleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage(),e);
        }
    }
    private void sendResultMsg(boolean result, StringBuilder sb) {
        if (result) {
            sb.append("<br>启动成功!");
            sendMsg(sb.toString(), MsgType.INFO);
        } else {
            sb.append("<br>启动失败!");
            sendMsg(sb.toString(), MsgType.ERROR);
        }
    }

    private ExecuteShellUtil getExecuteShellUtil(String ip) {
        ServerDeployDto serverDeployDTO = mntServerMapper.findByIp(ip);
        if (serverDeployDTO == null) {
            sendMsg("IP对应服务器信息不存在：" + ip, MsgType.ERROR);
            throw new BadRequestException("IP对应服务器信息不存在：" + ip);
        }
        return new ExecuteShellUtil(ip, serverDeployDTO.getAccount(), serverDeployDTO.getPassword(),serverDeployDTO.getPort());
    }
    private void sendMsg(String msg, MsgType msgType) {
        try {
            WebSocketServer.sendInfo(new SocketMsg(msg, msgType), "deploy");
        } catch (IOException e) {
            //throw e.printStackTrace();
        }
    }
    /**
     * 指定端口程序是否在运行
     *
     * @param port 端口
     * @param executeShellUtil /
     * @return true 正在运行  false 已经停止
     */
    private boolean checkIsRunningStatus(int port, ExecuteShellUtil executeShellUtil) {
        String result = executeShellUtil.executeForResult(String.format("fuser -n tcp %d", port));
        return result.indexOf("/tcp:")>0;
    }
    /**
     * 停App
     *
     * @param port 端口
     * @param executeShellUtil /
     */
    private void stopApp(int port, ExecuteShellUtil executeShellUtil) {
        //发送停止命令
        executeShellUtil.execute(String.format("lsof -i :%d|grep -v \"PID\"|awk '{print \"kill -9\",$2}'|sh", port));

    }
}
