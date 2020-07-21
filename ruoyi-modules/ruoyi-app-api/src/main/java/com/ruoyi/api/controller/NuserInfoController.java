package com.ruoyi.api.controller;


import com.ruoyi.api.db.model.SysNurse;
import com.ruoyi.api.dto.SysNurseDto;
import com.ruoyi.api.service.SysNurseService;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.annotation.Inner;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("nuser")
@Logger
public class NuserInfoController {
    @Autowired
    private SysNurseService sysNurseService;

    @Inner(false)
    @GetMapping(value = "/getNuserInfo")
    public AjaxResult getNuserInfo(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String queryType = request.getParameter("queryType");
        List<SysNurseDto> sysNursePageInfo =   sysNurseService.selectSysNurseList(userId,queryType);
        return AjaxResult.success("查询成功",sysNursePageInfo);
    }


    @Inner(false)
    @PostMapping(value = "/addInfo")
    public AjaxResult addInfo(HttpServletRequest request) throws ParseException {
        String userId = request.getParameter("userId");
        String ntype = request.getParameter("ntype");
        String dateTime = request.getParameter("dateTime");
        SysNurse sysNurse = new SysNurse();
        sysNurse.setUserid(Integer.valueOf(userId));
        sysNurse.setNtype(ntype);
        boolean success = sysNurseService.checkAdd(sysNurse);
        if(success){
            int i = sysNurseService.insertSysNurse(sysNurse,dateTime);
            //根据id 查询当前数据
            SysNurseDto sysNurse1 = sysNurseService.querySysNurseById(i);
            List<SysNurseDto> result = new ArrayList();
            result.add(sysNurse1);
            return AjaxResult.success("打卡成功",result);
        }else{
           return AjaxResult.error("还有未完成的任务在进行哦");
        }
    }
}
