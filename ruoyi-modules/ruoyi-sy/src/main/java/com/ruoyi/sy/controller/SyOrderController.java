package com.ruoyi.sy.controller;

import java.io.IOException;
import java.util.List;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.sy.domain.SyOrder;
import com.ruoyi.sy.domain.SyOrderDto;
import com.ruoyi.sy.service.ISyOrderService;
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
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2020-07-21
 */
@RestController
@RequestMapping("/order")
public class SyOrderController extends BaseController
{
    @Autowired
    private ISyOrderService syOrderService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('sy:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(SyOrder syOrder)
    {
        startPage();
        List<SyOrder> list = syOrderService.selectSyOrderList(syOrder);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('sy:order:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void export(SyOrder syOrder, HttpServletResponse response)
    {
        List<SyOrder> list = syOrderService.selectSyOrderList(syOrder);
        ExcelUtil<SyOrder> util = new ExcelUtil<SyOrder>(SyOrder.class);
        try {
             util.exportExcel(response,list, "order");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('sy:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(syOrderService.selectSyOrderById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('sy:order:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SyOrderDto syOrder)
    {
        return toAjax(syOrderService.insert(syOrder));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('sy:order:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SyOrder syOrder)
    {
        return toAjax(syOrderService.updateSyOrder(syOrder));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('sy:order:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(syOrderService.deleteSyOrderByIds(ids));
    }
}
