package com.smartrm.smartrmmonolith.infracore.scheduler;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.util.Map;

/**
 * @author: yoda
 * @description:
 */
public abstract class RetryExecutorBase extends QuartzJobBean {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RetryExecutorBase.class);
    
    // 必须手动管理事务
    @Autowired
    DataSourceTransactionManager transactionManager;
    
    @Autowired
    TransactionDefinition transactionDefinition;
    
    public abstract void run(Map<String, Object> params);
    
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        TransactionStatus transactionStatus = null;
        try {
            transactionStatus = transactionManager.getTransaction(transactionDefinition);   // 手动获取事务状态
            JobDataMap dataMap = context.getJobDetail().getJobDataMap();
            LOGGER.info("retrying...");
            run(dataMap);
            //如果执行成功，取消后续调度
            context.getScheduler().deleteJob(context.getJobDetail().getKey());
//      context.getScheduler().unscheduleJob(context.getTrigger().getKey());
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            LOGGER.error("execute failed.", e);
            //避免雪崩效应，不立即重试
            JobExecutionException jobException = new JobExecutionException(e);
            jobException.setRefireImmediately(false);
            if (transactionStatus != null) {
                transactionManager.rollback(transactionStatus);
            }
            throw jobException;
        }
    }
}
