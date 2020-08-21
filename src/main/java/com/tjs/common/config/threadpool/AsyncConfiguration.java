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
package com.tjs.common.config.threadpool;

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

}