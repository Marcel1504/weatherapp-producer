package com.github.marcel1504.weatherapp.producer.service.api.weather;

import com.github.marcel1504.weatherapp.producer.dto.weather.WeatherDTO;
import com.github.marcel1504.weatherapp.producer.dto.weather.cam.WeatherCamDTO;

import java.util.List;

public interface WeatherApiService {

    void putWeatherDataSync(List<WeatherDTO> weatherDTOS);

    void putWeatherCam(WeatherCamDTO dto);
}
