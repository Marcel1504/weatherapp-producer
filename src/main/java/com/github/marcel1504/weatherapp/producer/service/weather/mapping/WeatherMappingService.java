package com.github.marcel1504.weatherapp.producer.service.weather.mapping;

import com.github.marcel1504.weatherapp.producer.dto.weather.WeatherDTO;
import com.github.marcel1504.weatherapp.producer.entity.WeatherEntity;

import java.util.List;

public interface WeatherMappingService {

    WeatherDTO mapFromWeatherEntityToWeatherDTO(WeatherEntity entity);

    List<WeatherDTO> mapFromWeatherEntityToWeatherDTO(List<WeatherEntity> entity);
}
