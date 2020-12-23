package com.lingyu.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UUIDUtil {
    public static String get16UUID(){
        // 1.开头两位，标识业务代码或机器代码（可变参数）
        String machineId = "11";
        // 2.中间四位整数，标识日期
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        String dayTime = sdf.format(new Date());
        // 3.生成uuid的hashCode值
        int hashCode = UUID.randomUUID().toString().hashCode();
        // 4.可能为负数
        if(hashCode < 0){
            hashCode = -hashCode;
        }
        // 5.算法处理: 0-代表前面补充0; 10-代表长度为10; d-代表参数为正数型
        String value = machineId + dayTime + String.format("%010d", hashCode);
        return  value;
    }


}
