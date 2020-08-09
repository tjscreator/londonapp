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
package com.emotome.common.sms.batchjob;

import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.emotome.common.config.threadpool.AsyncConfiguration;
import com.emotome.common.logger.LoggerService;
import com.emotome.common.sms.model.SmsAccountModel;
import com.emotome.common.sms.model.SmsTransactionModel;
import com.emotome.common.sms.service.SmsAccountService;
import com.emotome.common.sms.service.SmsTransactionService;
import com.emotome.common.sms.thread.SmsThread;
import com.emotome.common.util.Constant;

/**
 * This class is used for sms transaction run.
 * 
 * @author jaydeep
 * @since 12/08/2019
 */
@DisallowConcurrentExecution
@Component
public class TransactionalSmsJob implements Job {

	@Autowired
	SmsTransactionService smsTransactionService;

	@Autowired
	SmsAccountService smsAccountService;

	@Autowired
	SmsThread smsThread;

	@Autowired
	ApplicationContext applicationContext;

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

		long startTime = System.currentTimeMillis();
		RestTemplate restTemplate = new RestTemplate();
		JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
		ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) applicationContext
				.getBean("transactionSmsExecutor");

		if (taskExecutor.getActiveCount() == taskExecutor.getMaxPoolSize() && taskExecutor.getThreadPoolExecutor()
				.getQueue().size() == new AsyncConfiguration().getTransactionSmsQueuesize()) {
			LoggerService.error("TransactionSmsJob", "Quartz scheduler", "Queue and pool is full.");
			return;
		}
		List<SmsTransactionModel> smsTransactionModels = smsTransactionService.getSmsList(10);
		for (SmsTransactionModel smsTransactionModel : smsTransactionModels) {
			SmsAccountModel smsAccountModel = smsAccountService.get(smsTransactionModel.getSmsAccountId());
			smsThread.sendTransactionSms(smsTransactionModel, smsAccountModel, restTemplate);
		}
		jobDataMap.put("Records", smsTransactionModels.size());
		jobDataMap.put(Constant.TOTAL_TIME_TAKEN_BY_JOB, System.currentTimeMillis() - startTime);
		return;
	}

}
