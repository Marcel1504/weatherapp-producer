package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather.calculation;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.entity.WeatherEntity;

import java.util.List;

public interface WeatherCalculationService {

    List<WeatherEntity> calculateRainfall(List<WeatherEntity> entities);

}
