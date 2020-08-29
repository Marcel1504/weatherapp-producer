package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.http.weather;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.dto.weather.WeatherDTO;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.dto.weather.cam.WeatherCamDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${weatherdataproducer.consumerService.url}")
    private String url;

    @Value("${weatherdataproducer.consumerService.username}")
    private String username;

    @Value("${weatherdataproducer.consumerService.password}")
    private String password;

    @Override
    public void putWeatherDataUpdate(List<WeatherDTO> weatherDTOS) {
        RestTemplate template = restTemplateBuilder.basicAuthentication(username, password).rootUri(url).build();
        template.put("/weather/update", weatherDTOS);
    }

    @Override
    public void putWeatherDataSync(List<WeatherDTO> weatherDTOS) {
        log.info("Start weather data synchronization with consumer at {}", url);
        RestTemplate template = restTemplateBuilder.basicAuthentication(username, password).rootUri(url).build();
        template.put("/weather/synchronize", weatherDTOS);
        log.info("Synchronized {} weather data items with consumer at {}", weatherDTOS.size(), url);
    }

    @Override
    public void putWeatherCam(WeatherCamDTO dto) {
        //headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        //body
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", dto.getFile());
        body.add("creationTimestamp", dto.getCreatedTimestamp().format(DateTimeFormatter.ISO_DATE_TIME));

        //request
        RestTemplate template = restTemplateBuilder.basicAuthentication(username, password).rootUri(url).build();
        template.put("/weathercam", body);
    }
}
