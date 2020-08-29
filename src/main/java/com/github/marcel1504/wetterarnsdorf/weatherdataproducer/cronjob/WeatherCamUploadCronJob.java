package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.cronjob;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather.cam.WeatherCamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WeatherCamUploadCronJob implements CronJob {

    @Autowired
    private WeatherCamService weatherCamService;

    @Scheduled(cron = "${weatherdataproducer.cronjob.weatherCamUpload}")
    @Override
    public void execute() {
        weatherCamService.uploadWeatherCam();
    }
}
