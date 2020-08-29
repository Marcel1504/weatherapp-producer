package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.http.weather;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.dto.weather.WeatherDTO;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.dto.weather.cam.WeatherCamDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class WeatherHttpServiceImpl implements WeatherHttpService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void putWeatherDataUpdate(List<WeatherDTO> weatherDTOS) {
        restTemplate.put("/weather/update", weatherDTOS);
    }

    @Override
    public void putWeatherDataSync(List<WeatherDTO> weatherDTOS) {
        log.info("Start weather data synchronization with consumer");
        restTemplate.put("/weather/synchronize", weatherDTOS);
        log.info("Synchronized {} weather data items with consumer", weatherDTOS.size());
    }

    @Override
    public void putWeatherCam(WeatherCamDTO dto) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", dto.getFile());
        body.add("creationTimestamp", dto.getCreatedTimestamp().format(DateTimeFormatter.ISO_DATE_TIME));

        //request
        restTemplate.put("/weathercam", body);
    }
}
