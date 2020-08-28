package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather.mapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.entity.WeatherEntity;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.exception.ServiceException;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class WeatherMappingServiceImpl implements WeatherMappingService {

    @Autowired
    private FileService fileService;

    @Autowired
    private Jackson2ObjectMapperBuilder objectMapperBuilder;

    @Override
    public WeatherEntity mapFromWeatherFileToEntity() {
        try {
            ObjectMapper objectMapper = objectMapperBuilder.build();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            objectMapper.setDateFormat(dateFormat);
            return objectMapperBuilder.build().readValue(fileService.getWeatherDataFile(), WeatherEntity.class);
        } catch (Exception e) {
            throw new ServiceException(String.format("Could not map weather file to entity: %s", e.getMessage()));
        }
    }
}
