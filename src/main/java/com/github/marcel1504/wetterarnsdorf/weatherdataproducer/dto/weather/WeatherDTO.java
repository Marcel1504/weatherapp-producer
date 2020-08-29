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
    private Integer rainHit;
    private Boolean isRain;
    private Integer rainfall;
    private Double wind;
    private LocalDateTime timestamp;
}
