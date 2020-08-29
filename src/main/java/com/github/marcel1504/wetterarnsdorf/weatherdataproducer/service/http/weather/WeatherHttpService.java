package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.http.weather;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.dto.weather.WeatherDTO;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

public interface WeatherHttpService {

    void putWeatherDataUpdate(List<WeatherDTO> weatherDTOS);

    void putWeatherDataSync(List<WeatherDTO> weatherDTOS);

    void putWeatherCam(File file, LocalDateTime createdTimestamp);
}
