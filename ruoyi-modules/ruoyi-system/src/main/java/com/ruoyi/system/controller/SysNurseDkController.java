package com.ruoyi.system.controller;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.system.domain.SysNurse;
import com.ruoyi.system.domain.vo.SysNurseVo;
import com.ruoyi.system.service.ISysNurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/nurseDk")
public class SysNurseDkController extends BaseController {
    @Autowired
    private ISysNurseService sysNurseService;
    /**
     * 新增【】
     */
    @PreAuthorize("@ss.hasPermi('system:nursedk:daka')")
    @Log(title = "【】", businessType = BusinessType.INSERT)
    @PostMapping("/godaka/{ntype}/{startDate}/{startTime}")
    public AjaxResult addDk(@DefaultValue ("")@PathVariable(value = "ntype", required = false) String nType,
                            @DefaultValue ("")@PathVariable(value = "startDate") String startDate,
                            @DefaultValue ("")@PathVariable(value = "startTime") String startTime) throws ParseException {
        SysNurse sysNurse = new SysNurse();
        sysNurse.setNtype(nType);
        System.out.printf(startDate);
        System.out.printf(startTime);


        boolean success = sysNurseService.checkAdd(sysNurse);
        if(success){
            return toAjax(sysNurseService.insertSysNurse(sysNurse,startDate,startTime));
        }
        return AjaxResult.error("还有未结束的任务，请先结束，在开始新的任务");

    }
    @PreAuthorize("@ss.hasPermi('system:nursedk:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysNurse sysNurse)
    {
        List<SysNurseVo> list = sysNurseService.selectSysNurseListLast();
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setRows(list);
        rspData.setTotal(list.size());
        return rspData;
    }
}
