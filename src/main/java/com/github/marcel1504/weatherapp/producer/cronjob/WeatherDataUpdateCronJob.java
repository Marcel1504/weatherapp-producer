package com.github.marcel1504.weatherapp.producer.cronjob;

import com.github.marcel1504.weatherapp.producer.service.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherDataUpdateCronJob implements CronJob {

    @Autowired
    private WeatherService weatherService;

    @Scheduled(cron = "${weatherapp.cronjob.weatherDataUpdate}")
    @Override
    public void execute() {
        weatherService.updateLatestWeatherData();
    }
}
