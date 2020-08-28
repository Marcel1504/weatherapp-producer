package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.repository.weather;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {

    boolean existsByTimestamp(Timestamp timestamp);
}
