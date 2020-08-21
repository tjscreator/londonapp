/*******************************************************************************
 * Copyright -2018 @Emotome
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.tjs.common.config.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import com.tjs.common.enums.EnvironmentEnum;
import com.tjs.common.sms.batchjob.TransactionalSmsJob;

/**
 * This class configures quartz scheduler properties.
 * 
 * @author nirav
 *
 */
@Component
@Configuration
public class SchedulerRegister implements ApplicationRunner {

	@Value("${app.environment}")
	private int environment;

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	public JobDetail transactionSmsJob() {
		JobKey jobKey = new JobKey("TransactionSms", "harbor");
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("jobName", "TransactionSms");
		return JobBuilder.newJob(TransactionalSmsJob.class).withIdentity(jobKey).setJobData(jobDataMap).build();
	}

	public Trigger transactionSmsCronTrigger() {
		return TriggerBuilder.newTrigger().withIdentity("TransactionSms", "harbor")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		scheduler.getListenerManager().addJobListener(new SchedulerJobListener());
		if (EnvironmentEnum.PRODUCTION.equals(EnvironmentEnum.fromId(environment))) {
			// Sms Transaction Job Details
			JobDetail transactionSmsJobDetail = transactionSmsJob();
			Trigger transactionSmsTrigger = transactionSmsCronTrigger();
			scheduler.scheduleJob(transactionSmsJobDetail, transactionSmsTrigger);

			// Appointment Sms Reminder Job Details
			/*
			 * JobDetail appointmentSmsReminderJobDetail = appointmentSmsReminderJob();
			 * Trigger appointmentSmsReminderTrigger = appointmentSmsReminderCronTrigger();
			 * scheduler.scheduleJob(appointmentSmsReminderJobDetail,
			 * appointmentSmsReminderTrigger);
			 * 
			 * // Appointment Fetch Record fetch job details JobDetail
			 * appointmentRecordFetchJobDetail = appointmentRecordFetchJob(); Trigger
			 * appointmentRecordFetchTrigger = appointmentRecordFetchCronTrigger();
			 * scheduler.scheduleJob(appointmentRecordFetchJobDetail,
			 * appointmentRecordFetchTrigger);
			 */
		}
	}
}