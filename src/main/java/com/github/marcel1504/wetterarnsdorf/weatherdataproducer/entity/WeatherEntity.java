package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "we_weather")
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "humidity")
    private Integer humidity;

    @Column(name = "rain_hit")
    private Integer rainHit;

    @Column(name = "is_rain")
    private Boolean isRain;

    @Column(name = "rainfall")
    private Integer rainfall;

    @Column(name = "wind")
    private Double wind;

    @Column(name = "timestamp")
    private Timestamp timestamp;
}
