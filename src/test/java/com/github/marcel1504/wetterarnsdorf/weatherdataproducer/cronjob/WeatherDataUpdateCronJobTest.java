package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.cronjob;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather.WeatherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WeatherDataUpdateCronJobTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherDataUpdateCronJob weatherDataUpdateCronJob = new WeatherDataUpdateCronJob();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void executeTest() {
        weatherDataUpdateCronJob.execute();
        verify(weatherService, times(1)).updateLatestWeatherData();
    }
}