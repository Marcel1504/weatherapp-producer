package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.validator.weather;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.entity.WeatherEntity;

import java.util.List;

public interface WeatherValidator {
    void validate(List<WeatherEntity> entities);

    void validate(WeatherEntity entity);
}
