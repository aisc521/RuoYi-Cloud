package com.ruoyi.maintenance.controller;

import java.io.IOException;
import java.util.List;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.maintenance.domain.MntApp;
import com.ruoyi.maintenance.service.IMntAppService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 应用管理Controller
 * 
 * @author ruoyi
 * @date 2020-07-17
 */
@RestController
@RequestMapping("/app")
public class MntAppController extends BaseController
{
    @Autowired
    private IMntAppService mntAppService;

    /**
     * 查询应用管理列表
     */
    @PreAuthorize("@ss.hasPermi('maintenance:app:list')")
    @GetMapping("/list")
    public TableDataInfo list(MntApp mntApp)
    {
        startPage();
        List<MntApp> list = mntAppService.selectMntAppList(mntApp);
        return getDataTable(list);
    }

    /**
     * 导出应用管理列表
     */
    @PreAuthorize("@ss.hasPermi('maintenance:app:export')")
    @Log(title = "应用管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void export(HttpServletResponse response,MntApp mntApp)
    {
        List<MntApp> list = mntAppService.selectMntAppList(mntApp);
        ExcelUtil<MntApp> util = new ExcelUtil<MntApp>(MntApp.class);
        try {
            util.exportExcel(response,list, "app");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取应用管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('maintenance:app:query')")
    @GetMapping(value = "/{appId}")
    public AjaxResult getInfo(@PathVariable("appId") Long appId)
    {
        return AjaxResult.success(mntAppService.selectMntAppById(appId));
    }

    /**
     * 新增应用管理
     */
    @PreAuthorize("@ss.hasPermi('maintenance:app:add')")
    @Log(title = "应用管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MntApp mntApp)
    {
        return toAjax(mntAppService.insertMntApp(mntApp));
    }

    /**
     * 修改应用管理
     */
    @PreAuthorize("@ss.hasPermi('maintenance:app:edit')")
    @Log(title = "应用管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MntApp mntApp)
    {
        return toAjax(mntAppService.updateMntApp(mntApp));
    }

    /**
     * 删除应用管理
     */
    @PreAuthorize("@ss.hasPermi('maintenance:app:remove')")
    @Log(title = "应用管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{appIds}")
    public AjaxResult remove(@PathVariable Long[] appIds)
    {
        return toAjax(mntAppService.deleteMntAppByIds(appIds));
    }
}
