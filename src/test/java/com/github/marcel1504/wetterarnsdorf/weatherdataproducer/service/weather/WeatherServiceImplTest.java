package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.entity.WeatherEntity;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.repository.weather.WeatherRepository;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.http.weather.WeatherHttpService;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather.calculation.WeatherCalculationService;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather.mapping.WeatherMappingService;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.validator.weather.WeatherValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceImplTest {

    @Mock
    private WeatherRepository weatherRepository;

    @Mock
    private WeatherMappingService weatherMappingService;

    @Mock
    private WeatherValidator weatherValidator;

    @Mock
    private WeatherCalculationService weatherCalculationService;

    @Mock
    private WeatherHttpService weatherHttpService;

    @InjectMocks
    private WeatherService weatherService = new WeatherServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private List<WeatherEntity> setupWeatherEntityList() {
        List<WeatherEntity> list = new ArrayList<>();
        list.add(WeatherEntity.builder()
                .id(1L)
                .temperature(1.0)
                .humidity(1)
                .isRain(false)
                .rainHit(1)
                .rainfall(1)
                .wind(1.0)
                .timestamp(Timestamp.valueOf("2020-01-01 12:00:00"))
                .build());
        return list;
    }

    @Test
    public void addDataFromWeatherFileTest() {
        WeatherEntity entity = setupWeatherEntityList().get(0);
        when(weatherMappingService.mapFromWeatherFileToEntity()).thenReturn(entity);
        when(weatherRepository.existsByTimestamp(entity.getTimestamp())).thenReturn(false);

        weatherService.addDataFromWeatherFile();

        verify(weatherMappingService, times(1)).mapFromWeatherFileToEntity();
        verify(weatherRepository, times(1)).existsByTimestamp(any());
        verify(weatherRepository, times(1)).save(entity);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void updateRainfallTest() {
        List<WeatherEntity> list = setupWeatherEntityList();
        when(weatherRepository.findAll(any(PageRequest.class))).thenReturn(mock(Page.class));
        when(weatherCalculationService.calculateRainfall(any())).thenReturn(list);

        weatherService.updateRainfall();

        verify(weatherCalculationService, times(1)).calculateRainfall(any());
        verify(weatherRepository, times(1)).saveAll(list);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void uploadWeatherDataTest() {
        when(weatherRepository.findAll(any(PageRequest.class))).thenReturn(mock(Page.class));

        weatherService.uploadWeatherData();

        verify(weatherHttpService, times(1)).putWeatherDataUpdate(any());
    }

    @Test
    public void syncWeatherDataTest() {
        weatherService.syncWeatherData();

        verify(weatherHttpService, times(1)).putWeatherDataSync(any());
    }
}