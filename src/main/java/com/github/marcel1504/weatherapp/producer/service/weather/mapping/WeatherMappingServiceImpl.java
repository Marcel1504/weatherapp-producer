package com.github.marcel1504.weatherapp.producer.service.weather.mapping;

import com.github.marcel1504.weatherapp.producer.dto.weather.WeatherDto;
import com.github.marcel1504.weatherapp.producer.entity.WeatherEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Service
public class WeatherMappingServiceImpl implements WeatherMappingService {

    @Value("${weatherapp.multiplier.rain}")
    private Double rainMultiplier;

    @Value("${weatherapp.multiplier.wind}")
    private Double windMultiplier;

    @Override
    public WeatherDto mapFromWeatherEntityToWeatherDTO(WeatherEntity entity) {
        return WeatherDto.builder()
                .temperature(entity.getTemperature())
                .humidity(entity.getHumidity())
                .rainfall(entity.getRainfall() == null ? 0.0 : entity.getRainfall() * rainMultiplier)
                .rainRate(entity.getRainRate() == null ? 0.0 : entity.getRainRate() * rainMultiplier)
                .wind(entity.getWind() == null ? 0.0 : entity.getWind() * windMultiplier)
                .windDirection(entity.getWindDirection())
                .pressure(entity.getPressure())
                .timestamp(LocalDateTime.ofInstant(
                        Instant.ofEpochSecond(entity.getDateTime()),
                        TimeZone.getDefault().toZoneId()))
                .build();
    }
}
