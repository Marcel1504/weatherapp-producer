package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.repository.weather.WeatherRepository;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.http.weather.WeatherHttpService;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather.mapping.WeatherMappingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceImplTest {

    @Mock
    private WeatherRepository weatherRepository;

    @Mock
    private WeatherMappingService weatherMappingService;

    @Mock
    private WeatherHttpService weatherHttpService;

    @InjectMocks
    private WeatherService weatherService = new WeatherServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void updateLatestWeatherDataTest() {
        weatherService.updateLatestWeatherData();

        verify(weatherHttpService, times(1)).putWeatherDataSync(any());
    }

    @Test
    public void syncWeatherDataTest() {
        weatherService.syncWeatherData();

        verify(weatherHttpService, times(1)).putWeatherDataSync(any());
    }
}