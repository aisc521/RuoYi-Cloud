package com.ruoyi.maintenance.controller;

import java.io.IOException;
import java.util.List;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.maintenance.domain.MntDeployHistory;
import com.ruoyi.maintenance.service.IMntDeployHistoryService;
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
 * 部署历史管理Controller
 * 
 * @author ruoyi
 * @date 2020-07-21
 */
@RestController
@RequestMapping("/system/history")
public class MntDeployHistoryController extends BaseController
{
    @Autowired
    private IMntDeployHistoryService mntDeployHistoryService;

    /**
     * 查询部署历史管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:history:list')")
    @GetMapping("/list")
    public TableDataInfo list(MntDeployHistory mntDeployHistory)
    {
        startPage();
        List<MntDeployHistory> list = mntDeployHistoryService.selectMntDeployHistoryList(mntDeployHistory);
        return getDataTable(list);
    }

    /**
     * 导出部署历史管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:history:export')")
    @Log(title = "部署历史管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void export(MntDeployHistory mntDeployHistory, HttpServletResponse response)
    {
        List<MntDeployHistory> list = mntDeployHistoryService.selectMntDeployHistoryList(mntDeployHistory);
        ExcelUtil<MntDeployHistory> util = new ExcelUtil<MntDeployHistory>(MntDeployHistory.class);
        try {
            util.exportExcel(response,list, "history");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取部署历史管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:history:query')")
    @GetMapping(value = "/{historyId}")
    public AjaxResult getInfo(@PathVariable("historyId") String historyId)
    {
        return AjaxResult.success(mntDeployHistoryService.selectMntDeployHistoryById(historyId));
    }

    /**
     * 新增部署历史管理
     */
    @PreAuthorize("@ss.hasPermi('system:history:add')")
    @Log(title = "部署历史管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MntDeployHistory mntDeployHistory)
    {
        return toAjax(mntDeployHistoryService.insertMntDeployHistory(mntDeployHistory));
    }

    /**
     * 修改部署历史管理
     */
    @PreAuthorize("@ss.hasPermi('system:history:edit')")
    @Log(title = "部署历史管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MntDeployHistory mntDeployHistory)
    {
        return toAjax(mntDeployHistoryService.updateMntDeployHistory(mntDeployHistory));
    }

    /**
     * 删除部署历史管理
     */
    @PreAuthorize("@ss.hasPermi('system:history:remove')")
    @Log(title = "部署历史管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{historyIds}")
    public AjaxResult remove(@PathVariable String[] historyIds)
    {
        return toAjax(mntDeployHistoryService.deleteMntDeployHistoryByIds(historyIds));
    }
}
