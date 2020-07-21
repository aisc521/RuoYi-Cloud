package com.ruoyi.maintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.security.annotation.EnableRyResourceServer;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 运维
 * 
 * @author ruoyi
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@EnableRyResourceServer
@SpringCloudApplication
public class RuoYiMaintenanceApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiMaintenanceApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  运维模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }
}
