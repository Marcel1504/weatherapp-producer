package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather.mapping;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.dto.weather.WeatherDTO;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.entity.WeatherEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Service
public class WeatherMappingServiceImpl implements WeatherMappingService {

    @Value("${weatherdataproducer.multiplier.rain}")
    private Double rainMultiplier;

    @Value("${weatherdataproducer.multiplier.wind}")
    private Double windMultiplier;

    @Override
    public WeatherDTO mapFromWeatherEntityToWeatherDTO(WeatherEntity entity) {
        return WeatherDTO.builder()
                .temperature(entity.getTemperature())
                .humidity(entity.getHumidity())
                .rainfall(entity.getRainfall() == null ? 0.0 : entity.getRainfall() * rainMultiplier)
                .wind(entity.getWind() == null ? 0.0 : entity.getWind() * windMultiplier)
                .windDirection(entity.getWindDirection())
                .pressure(entity.getPressure())
                .timestamp(LocalDateTime.ofInstant(
                        Instant.ofEpochSecond(entity.getDateTime()),
                        TimeZone.getDefault().toZoneId()))
                .build();
    }

    @Override
    public List<WeatherDTO> mapFromWeatherEntityToWeatherDTO(List<WeatherEntity> entity) {
        List<WeatherDTO> dtos = new ArrayList<>();
        entity.forEach(e -> dtos.add(mapFromWeatherEntityToWeatherDTO(e)));
        return dtos;
    }
}
