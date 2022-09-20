package com.smartrm.smartrmmonolith.infracore.scheduler;

import com.smartrm.smartrmmonolith.infracore.api.CommonError;
import com.smartrm.smartrmmonolith.infracore.exception.DomainException;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.Map;

import static org.quartz.DateBuilder.IntervalUnit;
import static org.quartz.DateBuilder.futureDate;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author: yoda
 * @description: 调度器
 */
@Component
public class ExecutorJobScheduler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorJobScheduler.class);
    
    @Autowired
    private Scheduler scheduler;
    
    @PostConstruct
    public void init() throws SchedulerException {
        LOGGER.info("scheduler starting.");
        scheduler.start();
        LOGGER.info("scheduler started.");
    }
    
    @PreDestroy
    public void destroy() throws SchedulerException {
        scheduler.shutdown();
    }
    
    public void schedule(Class<? extends ExecutorBase> jobClass, Map<String, Object> params,
                         int delay) {
        Date date = new Date(System.currentTimeMillis() + delay);
        SimpleTrigger trigger = (SimpleTrigger) newTrigger().startAt(
                        futureDate(delay, IntervalUnit.MILLISECOND))
                .build();
        JobDataMap dataMap = new JobDataMap(params);
        JobDetail job = JobBuilder.newJob(jobClass).setJobData(dataMap).build();
        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            LOGGER.error("scheduler error.", e);
            throw new DomainException(CommonError.SchedulerError);
        }
    }
    
    
    /**
     * 无限次调度重试任务
     *
     * @param jobClass      任务类
     * @param params        任务参数
     * @param delay         开始延迟，毫秒数
     * @param retryInterval 重试间隔，毫秒数
     */
    public void scheduleRetry(Class<? extends RetryExecutorBase> jobClass, Map<String, Object> params,
                              int delay, int retryInterval) {
        this.scheduleRetry(jobClass, params, delay, retryInterval, -1);
    }
    
    /**
     * 调度重试任务
     *
     * @param jobClass      任务类
     * @param params        任务参数
     * @param delay         开始延迟，毫秒数
     * @param retryInterval 重试间隔，毫秒数
     * @param retryTimes    重试次数，-1表示无限次，直到成功为止
     */
    public void scheduleRetry(Class<? extends RetryExecutorBase> jobClass, Map<String, Object> params,
                              int delay, int retryInterval, int retryTimes) {
        Date date = new Date(System.currentTimeMillis() + delay);
        SimpleTrigger trigger = (SimpleTrigger) newTrigger()
                .startAt(futureDate(delay, IntervalUnit.MILLISECOND))
                .withSchedule(
                        simpleSchedule().withIntervalInMilliseconds(retryInterval).withRepeatCount(retryTimes))
                .build();
        JobDataMap dataMap = new JobDataMap(params);
        JobDetail job = JobBuilder.newJob(jobClass).setJobData(dataMap).build();
        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            LOGGER.error("scheduler error.", e);
            throw new DomainException(CommonError.SchedulerError);
        }
    }
    
}
