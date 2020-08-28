package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather.mapping;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.entity.WeatherEntity;

public interface WeatherMappingService {
    WeatherEntity mapFromWeatherFileToEntity();
}
