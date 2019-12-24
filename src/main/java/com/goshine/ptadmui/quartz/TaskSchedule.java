package com.goshine.ptadmui.quartz;

import java.util.Date;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.goshine.ptadmui.core.utils.DateHandler;
/**
 * 定时任务调度类
 * 多个不同任务用不同方法区分即可
 */
@Component
@Configurable
@EnableScheduling
public class TaskSchedule{
    /**
     * 按照时间间隔执行任务调度
     * 每隔300秒执行一次
     */
    @Scheduled(fixedRate=1000*300)
    public void startTask(){
        System.out.println ("fixedRate Task is start,now time:"+DateHandler.formatDate("yyyy-MM-dd HH:mm:ss",new Date()));
        //在此写业务代码即可
    }

    /**
     * 根据时间规则执行任务调度
     * 每5分钟执行一次
     */
    @Scheduled(cron = "0 */5 *  * * * ")
    public void startCronTask(){
        System.out.println ("cron Task is start,now time: "+DateHandler.formatDate("yyyy-MM-dd HH:mm:ss",new Date()));
        //在此写业务代码即可
    }
}
