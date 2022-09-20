package com.smartrm.smartrmmonolith.infracore.scheduler;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Map;

/**
 * @author: yoda
 * @description: 执行器
 */
public abstract class ExecutorBase extends QuartzJobBean {
    
    public abstract void run(Map<String, Object> params);
    
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        try {
            run(dataMap);
        } catch (Exception e) {
            //避免雪崩效应，不重试
            JobExecutionException jobException = new JobExecutionException(e);
            jobException.setRefireImmediately(false);
            throw jobException;
        }
    }
}
