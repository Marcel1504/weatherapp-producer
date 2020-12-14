package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.dto.weather;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDTO {
    private Double temperature;
    private Integer humidity;
    private Double rainfall;
    private Double wind;
    private Integer windDirection;
    private Double pressure;
    private LocalDateTime timestamp;
}
