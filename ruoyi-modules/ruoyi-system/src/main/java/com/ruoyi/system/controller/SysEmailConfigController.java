package com.ruoyi.system.controller;

import java.io.IOException;
import java.util.List;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.system.service.ISysEmailConfigService;
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

import com.ruoyi.system.domain.SysEmailConfig;

import javax.servlet.http.HttpServletResponse;


/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2020-05-26
 */
@RestController
@RequestMapping("/email")
public class SysEmailConfigController extends BaseController
{
    @Autowired
    private ISysEmailConfigService sysEmailConfigService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:config:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysEmailConfig sysEmailConfig)
    {
        startPage();
        List<SysEmailConfig> list = sysEmailConfigService.selectSysEmailConfigList(sysEmailConfig);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:config:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void export(HttpServletResponse response,SysEmailConfig sysEmailConfig) throws IOException {
        List<SysEmailConfig> list = sysEmailConfigService.selectSysEmailConfigList(sysEmailConfig);
        ExcelUtil<SysEmailConfig> util = new ExcelUtil<SysEmailConfig>(SysEmailConfig.class);
        util.exportExcel(response,list, "config");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:config:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysEmailConfigService.selectSysEmailConfigById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:config:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysEmailConfig sysEmailConfig)
    {
        return toAjax(sysEmailConfigService.insertSysEmailConfig(sysEmailConfig));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:config:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysEmailConfig sysEmailConfig)
    {
        return toAjax(sysEmailConfigService.updateSysEmailConfig(sysEmailConfig));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:config:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysEmailConfigService.deleteSysEmailConfigByIds(ids));
    }
}
