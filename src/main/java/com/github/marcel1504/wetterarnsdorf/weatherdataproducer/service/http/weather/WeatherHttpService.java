package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.http.weather;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.dto.weather.WeatherDTO;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.dto.weather.cam.WeatherCamDTO;

import java.util.List;

public interface WeatherHttpService {

    void putWeatherDataUpdate(List<WeatherDTO> weatherDTOS);

    void putWeatherDataSync(List<WeatherDTO> weatherDTOS);

    void putWeatherCam(WeatherCamDTO dto);
}
