package com.github.marcel1504.weatherapp.producer.service.weather;

import com.github.marcel1504.weatherapp.producer.entity.WeatherEntity;
import com.github.marcel1504.weatherapp.producer.repository.weather.WeatherRepository;
import com.github.marcel1504.weatherapp.producer.service.api.weather.WeatherApiService;
import com.github.marcel1504.weatherapp.producer.service.weather.mapping.WeatherMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private WeatherMappingService weatherMappingService;

    @Autowired
    private WeatherApiService weatherApiService;

    private Long currentLatestDateTime;

    @Override
    public void updateLatestWeatherData() {
        WeatherEntity latest = weatherRepository.findLatest();
        if (latest != null && latest.getDateTime() != null) {
            if (currentLatestDateTime == null || latest.getDateTime() > currentLatestDateTime) {
                currentLatestDateTime = latest.getDateTime();
                log.info("Starting update weather data items with consumers");
                weatherApiService.postWeatherDataUpdate(weatherMappingService.mapFromWeatherEntityToWeatherDTO(latest));
            }
        }
    }
}
