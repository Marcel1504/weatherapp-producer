package com.github.marcel1504.weatherapp.producer.service.api.weather;

import com.github.marcel1504.weatherapp.producer.dto.weather.WeatherDTO;
import com.github.marcel1504.weatherapp.producer.dto.weather.cam.WeatherCamDTO;
import com.github.marcel1504.weatherapp.producer.service.rest.RestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class WeatherApiServiceImpl implements WeatherApiService {

    @Autowired
    private RestService restService;

    @Override
    public void putWeatherDataSync(List<WeatherDTO> weatherDTOS) {
        restService.putAll("/weather/synchronize", weatherDTOS);
    }

    @Override
    public void putWeatherCam(WeatherCamDTO dto) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", dto.getFile());
        body.add("creationTimestamp", dto.getCreatedTimestamp().format(DateTimeFormatter.ISO_DATE_TIME));

        //request
        restService.putAll("/weathercam", body);
    }
}
