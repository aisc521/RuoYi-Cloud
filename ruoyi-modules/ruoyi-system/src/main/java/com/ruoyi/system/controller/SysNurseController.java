package com.ruoyi.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.system.domain.vo.SysNurseVo;
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

import com.ruoyi.system.domain.SysNurse;
import com.ruoyi.system.service.ISysNurseService;

import javax.servlet.http.HttpServletResponse;


/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2020-05-22
 */
@RestController
@RequestMapping("/nurse")
public class SysNurseController extends BaseController
{
    @Autowired
    private ISysNurseService sysNurseService;

    /**
     * 查询【宝妈打卡】列表
     */
    @PreAuthorize("@ss.hasPermi('system:nurse:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysNurse sysNurse)
    {
        startPage();
        List<SysNurseVo> list = sysNurseService.selectSysNurseList(sysNurse);


        return getDataTable(list);
    }

    /**
     * 导出【宝妈打卡】列表
     */
    @PreAuthorize("@ss.hasPermi('system:nurse:export')")
    @Log(title = "【宝妈打卡】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void export(HttpServletResponse response,SysNurse sysNurse) throws IOException {
//        List<SysNurse> list = sysNurseService.selectSysNurseList(sysNurse);
//        ExcelUtil<SysNurse> util = new ExcelUtil<SysNurse>(SysNurse.class);
//         util.exportExcel(response,list, "nurse");
    }

    /**
     * 获取【宝妈打卡】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:nurse:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysNurseService.selectSysNurseById(id));
    }

    /**
     * 新增【宝妈打卡】
     */
    @PreAuthorize("@ss.hasPermi('system:nurse:add')")
    @Log(title = "【宝妈打卡】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysNurse sysNurse)
    {

        boolean success = sysNurseService.checkAdd(sysNurse);
        if(success){
            return toAjax(sysNurseService.insertSysNurse(sysNurse));
        }
        return AjaxResult.error("还有未结束的任务，请先结束，在开始新的任务");

    }

    /**
     * 修改【宝妈打卡】
     */
    @PreAuthorize("@ss.hasPermi('system:nurse:edit')")
    @Log(title = "【宝妈打卡】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysNurse sysNurse)
    {
        return toAjax(sysNurseService.updateSysNurse(sysNurse));
    }

    /**
     * 删除【宝妈打卡】
     */
    @PreAuthorize("@ss.hasPermi('system:nurse:remove')")
    @Log(title = "【宝妈打卡】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysNurseService.deleteSysNurseByIds(ids));
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("111");
        list.add("222");
        for (String item : list){
            if("222".equals(item)){
                list.remove(item);
            }
        }
        System.out.println(list);
    }
}
