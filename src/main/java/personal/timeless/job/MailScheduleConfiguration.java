package personal.timeless.job;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
/*
 配置类
 */
@Configuration
public class MailScheduleConfiguration {
	
	@Bean
    public MethodInvokingJobDetailFactoryBean jobDetail(MailSend mailSend) {//注入Task
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setGroup("mailJobGroup");
        jobDetail.setName("sendEmailJob");
        jobDetail.setTargetObject (mailSend);
        jobDetail.setTargetMethod ("execute");
        jobDetail.setConcurrent (false);
        return jobDetail;
    }
	
	@Bean
    public CronTriggerFactoryBean cronTriggerBean(MethodInvokingJobDetailFactoryBean jobDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean ();
        trigger.setJobDetail(jobDetail.getObject());
        trigger.setName("SendEmailCronTrigger");
        trigger.setGroup("mailCronTriggerGroup");
        
        try {
            trigger.setCronExpression ("*/1 * * * * ?");
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return trigger;
    }
	
	@Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(CronTriggerFactoryBean cronTriggerBean){
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean ();
        schedulerFactory.setTriggers(cronTriggerBean.getObject());
        schedulerFactory.setAutoStartup(false);
        return schedulerFactory;
    }
}
