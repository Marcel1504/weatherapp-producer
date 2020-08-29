package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.cronjob;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherDataSyncCronJob implements CronJob {

    @Autowired
    private WeatherService weatherService;

    @Scheduled(cron = "${weatherdataproducer.cronjob.weatherDataSync}")
    @Override
    public void execute() {
        weatherService.syncWeatherData();
    }
}