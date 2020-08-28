package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.cronjob;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WeatherCamUploadCronJob implements CronJob {

    @Scheduled(cron = "${weatherdataproducer.cronjob.weatherCamUpload}")
    @Override
    public void execute() {
        log.error("Run weatherCamUpload");
    }
}
