package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather.mapping;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.dto.weather.WeatherDTO;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.entity.WeatherEntity;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.exception.ServiceException;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherMappingServiceImpl implements WeatherMappingService {

    @Autowired
    private FileService fileService;

    @Autowired
    private Jackson2ObjectMapperBuilder objectMapperBuilder;

    @Override
    public WeatherEntity mapFromWeatherFileToEntity() {
        try {
            return objectMapperBuilder.build().readValue(fileService.getWeatherDataFile(), WeatherEntity.class);
        } catch (Exception e) {
            throw new ServiceException(String.format("Could not map weather file to entity: %s", e.getMessage()));
        }
    }

    @Override
    public WeatherDTO mapFromWeatherEntityToWeatherDTO(WeatherEntity entity) {
        return WeatherDTO.builder()
                .temperature(entity.getTemperature())
                .humidity(entity.getHumidity())
                .isRain(entity.getIsRain())
                .rainHit(entity.getRainHit())
                .rainfall(entity.getRainfall())
                .wind(entity.getWind())
                .timestamp(entity.getTimestamp().toLocalDateTime())
                .build();
    }

    @Override
    public List<WeatherDTO> mapFromWeatherEntityToWeatherDTO(List<WeatherEntity> entity) {
        List<WeatherDTO> dtos = new ArrayList<>();
        entity.forEach(e -> dtos.add(mapFromWeatherEntityToWeatherDTO(e)));
        return dtos;
    }
}
