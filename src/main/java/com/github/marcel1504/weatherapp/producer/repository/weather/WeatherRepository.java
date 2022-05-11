package com.github.marcel1504.weatherapp.producer.repository.weather;

import com.github.marcel1504.weatherapp.producer.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {

    @Override
    @Query(
            value = "SELECT " +
                    "round(w.outTemp,1) AS outTemp, " +
                    "round(w.outHumidity,0) AS outHumidity, " +
                    "round(w.barometer,1) AS barometer, " +
                    "round(w.rain,3) AS rain, " +
                    "round(w.windDir,0) AS windDir, " +
                    "round(w.windGust,1) AS windGust, " +
                    "w.dateTime " +
                    "FROM weatherdata w",
            countQuery = "SELECT * FROM weatherdata w",
            nativeQuery = true)
    List<WeatherEntity> findAll();

    @Query(
            value = "SELECT " +
                    "round(w.outTemp,1) AS outTemp, " +
                    "round(w.outHumidity,0) AS outHumidity, " +
                    "round(w.barometer,1) AS barometer, " +
                    "round(w.rain,3) AS rain, " +
                    "round(w.windDir,0) AS windDir, " +
                    "round(w.windGust,1) AS windGust, " +
                    "w.dateTime " +
                    "FROM weatherdata w " +
                    "ORDER BY w.dateTime DESC " +
                    "LIMIT :limit",
            countQuery = "SELECT * FROM weatherdata w " +
                    "ORDER BY w.dateTime DESC" +
                    "LIMIT :limit",
            nativeQuery = true)
    List<WeatherEntity> findLatestWeather(@Param(value = "limit") int limit);
}
