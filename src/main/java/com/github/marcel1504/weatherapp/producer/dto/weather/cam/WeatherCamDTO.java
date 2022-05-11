package com.github.marcel1504.weatherapp.producer.dto.weather.cam;

import lombok.*;
import org.springframework.core.io.FileSystemResource;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherCamDTO {
    private FileSystemResource file;
    private LocalDateTime createdTimestamp;
}
