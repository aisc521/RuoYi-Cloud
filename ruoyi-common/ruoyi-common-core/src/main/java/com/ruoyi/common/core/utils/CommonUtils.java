package com.ruoyi.common.core.utils;


import com.google.common.base.Strings;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springside.modules.utils.time.ClockUtil;
import org.springside.modules.utils.time.DateFormatUtil;
import java.util.*;

/**
 * Title:
 * Description:公共工具类
 * Copyright: Copyright (c) 2019
 * Company: 北京世纪中彩数据技术有限公司-互联网事业部
 *
 * @author Yore
 * @version 1.0
 * @Date 2019/3/20 11:31
 */
@Component
public class CommonUtils {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);





    /**
     * 订单号生
     * - //充值
     */
    public static String createOrderId(String type) {
        //年月日时分秒毫秒+椭机2位数+微秒三位
        String orderId = DateFormatUtil.formatDate("yyyyMMddHHmmssSSS", ClockUtil.currentDate()) + "-";
        String rd = String.valueOf(new Random().nextInt(99));
        if (rd.length() == 1) {
            rd = "0" + rd;
        }
        orderId = orderId + rd + (System.nanoTime() + "").substring(7, 10);
        if (!Strings.isNullOrEmpty(type)) {
            orderId = type + "-" + orderId;
        }
        return orderId;
    }



}
