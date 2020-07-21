package com.ruoyi.maintenance.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;

import cn.hutool.core.io.FileUtil;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.Inner;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.maintenance.domain.*;
import com.ruoyi.maintenance.service.IMntAppService;
import com.ruoyi.maintenance.service.IMntDeployServerService;
import com.ruoyi.maintenance.service.IMntDeployService;
import com.ruoyi.maintenance.service.IMntServerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 部署管理Controller
 * 
 * @author ruoyi
 * @date 2020-07-17
 */
@RestController
@RequestMapping("/deploy")
public class MntDeployController extends BaseController
{
    private final String fileSavePath = FileUtil.getTmpDirPath()+"/";
    @Autowired
    private IMntDeployService mntDeployService;

    @Autowired
    private IMntAppService iMntAppService;

    @Autowired
    private IMntServerService iMntServerService;
    @Autowired
    private IMntDeployServerService iMntDeployServerService;

    /**
     * 查询部署管理列表
     */
    @PreAuthorize("@ss.hasPermi('maintenance:deploy:list')")
    @GetMapping("/list")
    public TableDataInfo list(MntDeploy mntDeploy)
    {
        startPage();
        List<MntDeployDto> list = mntDeployService.selectMntDeployList(mntDeploy);
        return getDataTable(list);
    }

    /**
     * 导出部署管理列表
     */
    @PreAuthorize("@ss.hasPermi('maintenance:deploy:export')")
    @Log(title = "部署管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void export(HttpServletResponse response,MntDeploy mntDeploy)
    {
        List<MntDeployDto> list = mntDeployService.selectMntDeployList(mntDeploy);
        ExcelUtil<MntDeployDto> util = new ExcelUtil<MntDeployDto>(MntDeployDto.class);
        try {
            util.exportExcel(response,list, "deploy");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取部署管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('maintenance:deploy:query')")
    @GetMapping(value = "/{deployId}")
    public AjaxResult getInfo(@PathVariable("deployId") Long deployId)
    {
        return AjaxResult.success(mntDeployService.selectMntDeployById(deployId));
    }

    /**
     * 新增部署管理
     */
    @PreAuthorize("@ss.hasPermi('maintenance:deploy:add')")
    @Log(title = "部署管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Deploy mntDeploy, HttpServletRequest request)
    {
        return toAjax(mntDeployService.create(mntDeploy));
    }

    /**
     * 修改部署管理
     */
    @PreAuthorize("@ss.hasPermi('maintenance:deploy:edit')")
    @Log(title = "部署管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MntDeploy mntDeploy)
    {
        return toAjax(mntDeployService.updateMntDeploy(mntDeploy));
    }

    /**
     * 删除部署管理
     */
    @PreAuthorize("@ss.hasPermi('maintenance:deploy:remove')")
    @Log(title = "部署管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deployIds}")
    public AjaxResult remove(@PathVariable Long[] deployIds)
    {
        return toAjax(mntDeployService.deleteMntDeployByIds(deployIds));
    }

    @PreAuthorize("@ss.hasPermi('maintenance:deploy:queryApp')")
    @GetMapping("/queryApp")
    public TableDataInfo queryApp(MntDeploy mntDeploy)
    {
        List<MntApp> list = iMntAppService.queryAll();
        return getDataTable(list);
    }
    @PreAuthorize("@ss.hasPermi('maintenance:deploy:queryServer')")
    @GetMapping("/queryServer")
    public TableDataInfo queryServer(MntDeploy mntDeploy)
    {
        List<MntServer> list = iMntServerService.queryAll();
        return getDataTable(list);
    }

    @Log(title = "服务运行状态")
    @ApiOperation(value = "服务运行状态")
    @GetMapping(value = "/serverStatus/{query}")
    @PreAuthorize("@ss.hasPermi('maintenance:deploy:serverStatus')")
    public ResponseEntity<Object> serverStatus(@PathVariable("query") Long query){
        Deploy deploy = new Deploy();
        deploy.setId(query);
        //根据id 拼装 Deploy信息
        MntDeploy mntDeploy = mntDeployService.selectMntDeployById(query);
        if(mntDeploy == null){
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
        App app = iMntAppService.selectMntAppByIdForApp(mntDeploy.getAppId());
        if(app == null){
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
        deploy.setApp(app);
        MntDeployServer mntDeployServer = new MntDeployServer();
        mntDeployServer.setDeployId(query);
        List<MntDeployServer> mntDeployServerList = iMntDeployServerService.selectMntDeployServerList(mntDeployServer);
        if(mntDeployServerList.size() > 0){
            Set<ServerDeploy> deploys = new HashSet<ServerDeploy>();
            for (int i = 0; i < mntDeployServerList.size(); i++) {
                ServerDeploy serverDeploy = iMntServerService.selectServerDeploybyId(mntDeployServerList.get(i).getServerId());
                deploys.add(serverDeploy);
            }
            deploy.setDeploys(deploys);
            String result = mntDeployService.serverStatus(deploy);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
    }

    @Log(title = "启动服务")
    @ApiOperation(value = "启动服务")
    @GetMapping(value = "/startServer/{query}")
    @PreAuthorize("@ss.hasPermi('maintenance:deploy:startServer')")
    public ResponseEntity<Object> startServer(@PathVariable("query") Long query){
        Deploy deploy = new Deploy();
        deploy.setId(query);
        //根据id 拼装 Deploy信息
        MntDeploy mntDeploy = mntDeployService.selectMntDeployById(query);
        if(mntDeploy == null){
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
        App app = iMntAppService.selectMntAppByIdForApp(mntDeploy.getAppId());
        if(app == null){
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
        deploy.setApp(app);
        MntDeployServer mntDeployServer = new MntDeployServer();
        mntDeployServer.setDeployId(query);
        List<MntDeployServer> mntDeployServerList = iMntDeployServerService.selectMntDeployServerList(mntDeployServer);
        if(mntDeployServerList.size() > 0){
            Set<ServerDeploy> deploys = new HashSet<ServerDeploy>();
            for (int i = 0; i < mntDeployServerList.size(); i++) {
                ServerDeploy serverDeploy = iMntServerService.selectServerDeploybyId(mntDeployServerList.get(i).getServerId());
                deploys.add(serverDeploy);
            }
            deploy.setDeploys(deploys);
            String result = mntDeployService.startServer(deploy);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
    }

    @Log(title = "停止服务")
    @ApiOperation(value = "停止服务")
    @GetMapping(value = "/stopServer/{query}")
    @PreAuthorize("@ss.hasPermi('maintenance:deploy:stopServer')")
    public ResponseEntity<Object> stopServer(@PathVariable("query") Long query){

        Deploy deploy = new Deploy();
        deploy.setId(query);
        //根据id 拼装 Deploy信息
        MntDeploy mntDeploy = mntDeployService.selectMntDeployById(query);
        if(mntDeploy == null){
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
        App app = iMntAppService.selectMntAppByIdForApp(mntDeploy.getAppId());
        if(app == null){
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
        deploy.setApp(app);
        MntDeployServer mntDeployServer = new MntDeployServer();
        mntDeployServer.setDeployId(query);
        List<MntDeployServer> mntDeployServerList = iMntDeployServerService.selectMntDeployServerList(mntDeployServer);
        if(mntDeployServerList.size() > 0){
            Set<ServerDeploy> deploys = new HashSet<ServerDeploy>();
            for (int i = 0; i < mntDeployServerList.size(); i++) {
                ServerDeploy serverDeploy = iMntServerService.selectServerDeploybyId(mntDeployServerList.get(i).getServerId());
                deploys.add(serverDeploy);
            }
            deploy.setDeploys(deploys);
            String result = mntDeployService.stopServer(deploy);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }

    }

    @Log(title = "上传文件部署")
    @ApiOperation(value = "上传文件部署")
    @PostMapping(value = "/upload")
    @Inner(false)
    public ResponseEntity<Object> upload(@RequestBody MultipartFile file, HttpServletRequest request)throws Exception{
        Long id = Long.valueOf(request.getParameter("id"));
        String fileName = "";
        if(file != null){
            fileName = file.getOriginalFilename();
            File deployFile = new File(fileSavePath+fileName);
            FileUtil.del(deployFile);
            file.transferTo(deployFile);
            //文件下一步要根据文件名字来
            mntDeployService.deploy(fileSavePath+fileName ,id);
        }else{
            System.out.println("没有找到相对应的文件");
        }
        System.out.println("文件上传的原名称为:"+ Objects.requireNonNull(file).getOriginalFilename());
        Map<String,Object> map = new HashMap<>(2);
        map.put("errno",0);
        map.put("id",fileName);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

}
