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
public class WeatherDataSyncCronJobTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherDataSyncCronJob weatherDataSyncCronJob = new WeatherDataSyncCronJob();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void executeTest() {
        weatherDataSyncCronJob.execute();
        verify(weatherService, times(1)).syncWeatherData();
    }
}