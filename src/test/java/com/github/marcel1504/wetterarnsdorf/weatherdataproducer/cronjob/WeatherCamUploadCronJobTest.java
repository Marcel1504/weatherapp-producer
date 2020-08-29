package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.cronjob;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather.cam.WeatherCamService;
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
public class WeatherCamUploadCronJobTest {

    @Mock
    private WeatherCamService weatherCamService;

    @InjectMocks
    private WeatherCamUploadCronJob weatherCamUploadCronJob = new WeatherCamUploadCronJob();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void executeTest() {
        weatherCamUploadCronJob.execute();
        verify(weatherCamService, times(1)).uploadWeatherCam();
    }
}