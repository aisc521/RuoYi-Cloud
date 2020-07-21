package com.ruoyi.api.controller;


import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.security.annotation.Inner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {



    @GetMapping("/getInfo")
    @Inner(false)
    public void list()
    {
        System.out.printf("请求成功");
    }

}
