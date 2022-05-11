package com.github.marcel1504.weatherapp.producer.service.weather;

import com.github.marcel1504.weatherapp.producer.entity.WeatherEntity;
import com.github.marcel1504.weatherapp.producer.repository.weather.WeatherRepository;
import com.github.marcel1504.weatherapp.producer.service.http.weather.WeatherHttpService;
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
    private WeatherHttpService weatherHttpService;

    @Override
    public void updateLatestWeatherData() {
        weatherHttpService.putWeatherDataSync(
                weatherMappingService.mapFromWeatherEntityToWeatherDTO(weatherRepository.findLatestWeather(15))
        );
    }

    @Override
    public void syncWeatherData() {
        List<WeatherEntity> list = weatherRepository.findAll();
        weatherHttpService.putWeatherDataSync(
                weatherMappingService.mapFromWeatherEntityToWeatherDTO(list));
        log.info("Started synchronization of {} weather data items with consumer", list.size());
    }
}
