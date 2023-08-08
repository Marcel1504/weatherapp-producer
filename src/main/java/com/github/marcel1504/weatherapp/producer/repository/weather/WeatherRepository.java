package com.github.marcel1504.weatherapp.producer.repository.weather;

import com.github.marcel1504.weatherapp.producer.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {

    @Query(
            value = "SELECT " +
                    "round(w.outTemp,1) AS outTemp, " +
                    "round(w.outHumidity,0) AS outHumidity, " +
                    "round(w.barometer,1) AS barometer, " +
                    "round(w.rain,3) AS rain, " +
                    "round(w.rainRate,3) AS rainRate, " +
                    "round(w.windDir,0) AS windDir, " +
                    "round(w.windGust,1) AS windGust, " +
                    "w.dateTime " +
                    "FROM archive w " +
                    "ORDER BY w.dateTime DESC " +
                    "LIMIT 1",
            countQuery = "SELECT * FROM archive w " +
                    "WHERE w.dateTime >= :dateTime " +
                    "ORDER BY w.dateTime DESC",
            nativeQuery = true)
    WeatherEntity findLatest();
}
