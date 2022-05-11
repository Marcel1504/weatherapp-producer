package com.github.marcel1504.weatherapp.producer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Slf4j
@EnableScheduling
@Configuration
public class CronJobConfiguration implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setErrorHandler(t -> log.error("CronJob Exception: {}", t.getMessage()));
        taskScheduler.setThreadNamePrefix("cronjob-");
        taskScheduler.initialize();
        taskRegistrar.setScheduler(taskScheduler);
        taskScheduler.initialize();
    }
}
