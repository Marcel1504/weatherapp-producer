package com.github.marcel1504.weatherapp.producer.service.weather.cam;

import com.github.marcel1504.weatherapp.producer.dto.weather.cam.WeatherCamDTO;
import com.github.marcel1504.weatherapp.producer.service.file.FileService;
import com.github.marcel1504.weatherapp.producer.service.http.weather.WeatherHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class WeatherCamServiceImpl implements WeatherCamService {

    @Autowired
    private FileService fileService;

    @Autowired
    private WeatherHttpService weatherHttpService;

    @Override
    public void uploadWeatherCam() {
        File file = fileService.getWeatherCamFile();

        WeatherCamDTO dto = WeatherCamDTO.builder()
                .file(new FileSystemResource(file))
                .createdTimestamp(fileService.getCreationTimeOfFile(file))
                .build();

        weatherHttpService.putWeatherCam(dto);
    }
}
