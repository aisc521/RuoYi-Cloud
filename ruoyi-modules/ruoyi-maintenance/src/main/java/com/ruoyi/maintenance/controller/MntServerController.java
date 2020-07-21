package com.ruoyi.maintenance.controller;

import java.io.IOException;
import java.util.List;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.maintenance.domain.MntServer;
import com.ruoyi.maintenance.service.IMntServerService;
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

import javax.servlet.http.HttpServletResponse;


/**
 * 服务器管理Controller
 * 
 * @author ruoyi
 * @date 2020-07-16
 */
@RestController
@RequestMapping("/server")
public class MntServerController extends BaseController
{
    @Autowired
    private IMntServerService mntServerService;

    /**
     * 查询服务器管理列表
     */
    @PreAuthorize("@ss.hasPermi('maintenance:server:list')")
    @GetMapping("/list")
    public TableDataInfo list(MntServer mntServer)
    {
        startPage();
        List<MntServer> list = mntServerService.selectMntServerList(mntServer);
        return getDataTable(list);
    }

    /**
     * 导出服务器管理列表
     */
    @PreAuthorize("@ss.hasPermi('maintenance:server:export')")
    @Log(title = "服务器管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void export(HttpServletResponse response, MntServer mntServer)
    {
        List<MntServer> list = mntServerService.selectMntServerList(mntServer);
        ExcelUtil<MntServer> util = new ExcelUtil<MntServer>(MntServer.class);
        try {
            util.exportExcel(response,list, "server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取服务器管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('maintenance:server:query')")
    @GetMapping(value = "/{serverId}")
    public AjaxResult getInfo(@PathVariable("serverId") Long serverId)
    {
        return AjaxResult.success(mntServerService.selectMntServerById(serverId));
    }

    /**
     * 新增服务器管理
     */
    @PreAuthorize("@ss.hasPermi('maintenance:server:add')")
    @Log(title = "服务器管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MntServer mntServer)
    {
        return toAjax(mntServerService.insertMntServer(mntServer));
    }

    /**
     * 修改服务器管理
     */
    @PreAuthorize("@ss.hasPermi('maintenance:server:edit')")
    @Log(title = "服务器管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MntServer mntServer)
    {
        return toAjax(mntServerService.updateMntServer(mntServer));
    }

    /**
     * 删除服务器管理
     */
    @PreAuthorize("@ss.hasPermi('maintenance:server:remove')")
    @Log(title = "服务器管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{serverIds}")
    public AjaxResult remove(@PathVariable Long[] serverIds)
    {
        return toAjax(mntServerService.deleteMntServerByIds(serverIds));
    }

    /**
     * 测试连接
     * @param
     * @return
     */
    @PreAuthorize("@ss.hasPermi('maintenance:server:testConnect')")
    @Log(title = "服务器测试连接", businessType = BusinessType.TEST_CONNECT)
    @PostMapping("/testConnect")
    public AjaxResult testConnect(@Validated @RequestBody MntServer resources)
    {
        Boolean flag = mntServerService.testConnect(resources);
        if(flag){
            return AjaxResult.success("连接成功");
        }else{
            return AjaxResult.error("连接失败");
        }
    }
}
