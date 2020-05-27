package com.ruoyi.system.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.system.domain.SysNurse;
import com.ruoyi.system.service.ISysNurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/nurseDk")
public class SysNurseDkController extends BaseController {
    @Autowired
    private ISysNurseService sysNurseService;
    /**
     * 新增【宝妈打卡】
     */
    @PreAuthorize("@ss.hasPermi('system:nursedk:daka')")
    @Log(title = "【宝妈打卡】", businessType = BusinessType.INSERT)
    @PostMapping("/godaka/{ntype}")
    public AjaxResult addDk(@PathVariable(value = "ntype", required = false) String nType)
    {
        SysNurse sysNurse = new SysNurse();
        sysNurse.setNtype(nType);
        boolean success = sysNurseService.checkAdd(sysNurse);
        if(success){
            return toAjax(sysNurseService.insertSysNurse(sysNurse));
        }
        return AjaxResult.error("还有未结束的任务，请先结束，在开始新的任务");

    }
}
