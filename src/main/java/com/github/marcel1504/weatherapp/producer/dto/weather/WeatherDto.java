package com.github.marcel1504.weatherapp.producer.dto.weather;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDto {
    private Double temperature;
    private Integer humidity;
    private Double rainfall;
    private Double rainRate;
    private Double wind;
    private Integer windDirection;
    private Double pressure;
    private LocalDateTime timestamp;
}
