package com.github.marcel1504.weatherapp.producer.service.weather;

import com.github.marcel1504.weatherapp.producer.entity.WeatherEntity;
import com.github.marcel1504.weatherapp.producer.repository.weather.WeatherRepository;
import com.github.marcel1504.weatherapp.producer.service.api.weather.WeatherApiService;
import com.github.marcel1504.weatherapp.producer.service.weather.mapping.WeatherMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
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

    @Value("${weatherapp.limits.weatherDataUpdateMaxAgeHours}")
    private Integer weatherDataUpdateMaxAgeHours;

    @Value("${weatherapp.limits.weatherDataSyncMaxAgeHours}")
    private Integer weatherDataSyncMaxAgeHours;

    @Override
    public void updateLatestWeatherData() {
        Long dateTime = LocalDateTime
                .now()
                .minusHours(weatherDataUpdateMaxAgeHours)
                .toEpochSecond(OffsetDateTime.now().getOffset());
        List<WeatherEntity> list = weatherRepository.findAllAfter(dateTime);
        log.info("Starting update of {} weather data items with consumers", list.size());
        weatherApiService.putWeatherDataSync(weatherMappingService.mapFromWeatherEntityToWeatherDTO(list));
    }

    @Override
    public void syncWeatherData() {
        Long dateTime = LocalDateTime
                .now()
                .minusHours(weatherDataSyncMaxAgeHours)
                .toEpochSecond(OffsetDateTime.now().getOffset());
        List<WeatherEntity> list = weatherRepository.findAllAfter(dateTime);
        log.info("Starting synchronization of {} weather data items with consumers", list.size());
        weatherApiService.putWeatherDataSync(weatherMappingService.mapFromWeatherEntityToWeatherDTO(list));
    }
}
