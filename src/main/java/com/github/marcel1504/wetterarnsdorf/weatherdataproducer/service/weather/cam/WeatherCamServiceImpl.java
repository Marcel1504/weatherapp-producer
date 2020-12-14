package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather.cam;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.dto.weather.cam.WeatherCamDTO;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.file.FileService;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.http.weather.WeatherHttpService;
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

    @Value("${weatherdataproducer.locationKey}")
    private String locationKey;

    @Override
    public void uploadWeatherCam() {
        File file = fileService.getWeatherCamFile();

        WeatherCamDTO dto = WeatherCamDTO.builder()
                .file(new FileSystemResource(file))
                .createdTimestamp(fileService.getCreationTimeOfFile(file))
                .locationKey(locationKey)
                .build();

        weatherHttpService.putWeatherCam(dto);
    }
}
