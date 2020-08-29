package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather.mapping;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.dto.weather.WeatherDTO;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.entity.WeatherEntity;

import java.util.List;

public interface WeatherMappingService {
    WeatherEntity mapFromWeatherFileToEntity();

    WeatherDTO mapFromWeatherEntityToWeatherDTO(WeatherEntity entity);

    List<WeatherDTO> mapFromWeatherEntityToWeatherDTO(List<WeatherEntity> entity);
}
