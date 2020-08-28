package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.entity.WeatherEntity;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.repository.weather.WeatherRepository;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather.calculation.WeatherCalculationService;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather.mapping.WeatherMappingService;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.validator.weather.WeatherValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private WeatherMappingService weatherMappingService;

    @Autowired
    private WeatherValidator weatherValidator;

    @Autowired
    private WeatherCalculationService weatherCalculationService;

    @Override
    public void addDataFromWeatherFile() {
        WeatherEntity newWeather = weatherMappingService.mapFromWeatherFileToEntity();
        weatherValidator.validate(newWeather);

        if (!weatherRepository.existsByTimestamp(newWeather.getTimestamp())) {
            weatherRepository.save(newWeather);
        } else {
            log.warn("Skipped insert of new weather data as timestamp of the data already exists in the database");
        }
    }

    @Override
    public void updateRainfall() {
        List<WeatherEntity> updatedEntities = weatherCalculationService.calculateRainfall(
                weatherRepository.findAll(PageRequest.of(
                        0,
                        5,
                        Sort.by(Sort.Direction.DESC, "timestamp"))).getContent());
        weatherRepository.saveAll(updatedEntities);
    }

    @Override
    public void uploadWeatherData() {

    }
}
