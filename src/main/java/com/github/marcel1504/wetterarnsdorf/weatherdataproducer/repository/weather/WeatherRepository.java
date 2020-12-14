package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.repository.weather;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {

    @Query(
            value = "SELECT * FROM weatherdata " +
                    "ORDER BY dateTime DESC " +
                    "LIMIT :limit",
            countQuery = "SELECT * FROM weatherdata " +
                    "ORDER BY dateTime DESC" +
                    "LIMIT :limit",
            nativeQuery = true)
    List<WeatherEntity> findLatestWeather(@Param(value = "limit") int limit);
}
