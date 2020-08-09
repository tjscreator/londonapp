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
package com.emotome.common.config.threadpool;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * This class contains all sync method configurations.
 * 
 * @author Nirav.Shah
 * @since 15/05/2019
 */
@ComponentScan
@Configuration
@EnableAsync(proxyTargetClass = true)
@PropertySource(value = { "file:${catalina.base}/conf/harbor/thread-pool-configuration.properties" })
public class AsyncConfiguration {

	@Value("${transaction.sms.executor.corepoolsize}")
	private int transactionSmsCorePoolSize;

	@Value("${transaction.sms.executor.maxpoolsize}")
	private int transactionSmsMaxPoolSize;

	@Value("${transaction.sms.executor.queuesize}")
	private int transactionSmsQueuesize;

	@Value("${appointmentsmsreminder.executor.corepoolsize}")
	private int appointmentSmsReminderCorePoolSize;

	@Value("${appointmentsmsreminder.executor.maxpoolsize}")
	private int appointmentSmsReminderMaxPoolSize;

	@Value("${appointmentsmsreminder.executor.queuesize}")
	private int appointmentSmsReminderQueuesize;

	@Value("${appointmentrecordfetch.executor.corepoolsize}")
	private int appointmentRecordFetchCorePoolSize;

	@Value("${appointmentrecordfetch.executor.maxpoolsize}")
	private int appointmentRecordFetchMaxPoolSize;

	@Value("${appointmentrecordfetch.executor.queuesize}")
	private int appointmentRecordFetchQueuesize;

	@Bean(name = "transactionSmsExecutor")
	public Executor transactionSmsExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(transactionSmsCorePoolSize);
		executor.setMaxPoolSize(transactionSmsMaxPoolSize);
		executor.setQueueCapacity(transactionSmsQueuesize);
		executor.setThreadNamePrefix("TransactionSmsThread-");
		executor.initialize();
		return executor;
	}

	@Bean(name = "appointmentSmsReminderExecutor")
	public Executor appointmentReminderExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(appointmentSmsReminderCorePoolSize);
		executor.setMaxPoolSize(appointmentSmsReminderMaxPoolSize);
		executor.setQueueCapacity(appointmentSmsReminderQueuesize);
		executor.setThreadNamePrefix("appointmentSmsReminderThread-");
		executor.initialize();
		return executor;
	}

	@Bean(name = "appointmentRecordFetchExecutor")
	public Executor appointmentRecordFetchExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(appointmentRecordFetchCorePoolSize);
		executor.setMaxPoolSize(appointmentRecordFetchMaxPoolSize);
		executor.setQueueCapacity(appointmentRecordFetchQueuesize);
		executor.setThreadNamePrefix("appointmentRecordFetchThread-");
		executor.initialize();
		return executor;
	}

	public int getTransactionSmsCorePoolSize() {
		return transactionSmsCorePoolSize;
	}

	public void setTransactionSmsCorePoolSize(int transactionSmsCorePoolSize) {
		this.transactionSmsCorePoolSize = transactionSmsCorePoolSize;
	}

	public int getTransactionSmsMaxPoolSize() {
		return transactionSmsMaxPoolSize;
	}

	public void setTransactionSmsMaxPoolSize(int transactionSmsMaxPoolSize) {
		this.transactionSmsMaxPoolSize = transactionSmsMaxPoolSize;
	}

	public int getTransactionSmsQueuesize() {
		return transactionSmsQueuesize;
	}

	public void setTransactionSmsQueuesize(int transactionSmsQueuesize) {
		this.transactionSmsQueuesize = transactionSmsQueuesize;
	}

	public int getAppointmentSmsReminderMaxPoolSize() {
		return appointmentSmsReminderMaxPoolSize;
	}

	public void setAppointmentSmsReminderMaxPoolSize(int appointmentSmsReminderMaxPoolSize) {
		this.appointmentSmsReminderMaxPoolSize = appointmentSmsReminderMaxPoolSize;
	}

	public int getAppointmentSmsReminderQueuesize() {
		return appointmentSmsReminderQueuesize;
	}

	public void setAppointmentSmsReminderQueuesize(int appointmentSmsReminderQueuesize) {
		this.appointmentSmsReminderQueuesize = appointmentSmsReminderQueuesize;
	}

	public int getAppointmentRecordFetchCorePoolSize() {
		return appointmentRecordFetchCorePoolSize;
	}

	public void setAppointmentRecordFetchCorePoolSize(int appointmentRecordFetchCorePoolSize) {
		this.appointmentRecordFetchCorePoolSize = appointmentRecordFetchCorePoolSize;
	}

	public int getAppointmentRecordFetchMaxPoolSize() {
		return appointmentRecordFetchMaxPoolSize;
	}

	public void setAppointmentRecordFetchMaxPoolSize(int appointmentRecordFetchMaxPoolSize) {
		this.appointmentRecordFetchMaxPoolSize = appointmentRecordFetchMaxPoolSize;
	}

	public int getAppointmentRecordFetchQueuesize() {
		return appointmentRecordFetchQueuesize;
	}

	public void setAppointmentRecordFetchQueuesize(int appointmentRecordFetchQueuesize) {
		this.appointmentRecordFetchQueuesize = appointmentRecordFetchQueuesize;
	}

	public int getAppointmentSmsReminderCorePoolSize() {
		return appointmentSmsReminderCorePoolSize;
	}

	public void setAppointmentSmsReminderCorePoolSize(int appointmentSmsReminderCorePoolSize) {
		this.appointmentSmsReminderCorePoolSize = appointmentSmsReminderCorePoolSize;
	}

}