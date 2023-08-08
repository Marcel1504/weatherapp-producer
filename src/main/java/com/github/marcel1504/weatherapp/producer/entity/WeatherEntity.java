package com.github.marcel1504.weatherapp.producer.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "archive")
public class WeatherEntity {

    @Id
    @Column(name = "dateTime")
    private Long dateTime;

    @Column(name = "outTemp")
    private Double temperature;

    @Column(name = "outHumidity")
    private Integer humidity;

    @Column(name = "rain")
    private Double rainfall;

    @Column(name = "rainRate")
    private Double rainRate;

    @Column(name = "windGust")
    private Double wind;

    @Column(name = "windDir")
    private Integer windDirection;

    @Column(name = "barometer")
    private Double pressure;
}
