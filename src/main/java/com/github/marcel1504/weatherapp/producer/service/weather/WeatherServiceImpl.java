package com.github.marcel1504.weatherapp.producer.service.weather;

import com.github.marcel1504.weatherapp.producer.entity.WeatherEntity;
import com.github.marcel1504.weatherapp.producer.repository.weather.WeatherRepository;
import com.github.marcel1504.weatherapp.producer.service.api.weather.WeatherApiService;
import com.github.marcel1504.weatherapp.producer.service.weather.mapping.WeatherMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private WeatherMappingService weatherMappingService;

    @Autowired
    private WeatherApiService weatherApiService;

    @Override
    public void updateLatestWeatherData() {
        weatherApiService.putWeatherDataSync(
                weatherMappingService.mapFromWeatherEntityToWeatherDTO(weatherRepository.findLatestWeather(15))
        );
    }

    @Override
    public void syncWeatherData() {
        List<WeatherEntity> list = weatherRepository.findAll();
        log.info("Starting synchronization of {} weather data items with consumers", list.size());
        weatherApiService.putWeatherDataSync(
                weatherMappingService.mapFromWeatherEntityToWeatherDTO(list));
    }
}
