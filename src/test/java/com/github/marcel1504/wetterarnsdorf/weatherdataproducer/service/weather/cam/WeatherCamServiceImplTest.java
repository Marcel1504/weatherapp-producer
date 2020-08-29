package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather.cam;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.dto.weather.cam.WeatherCamDTO;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.file.FileService;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.http.weather.WeatherHttpService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WeatherCamServiceImplTest {

    @Mock
    private FileService fileService;

    @Mock
    private WeatherHttpService weatherHttpService;

    @InjectMocks
    private WeatherCamService weatherCamService = new WeatherCamServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void uploadWeatherCamTest() {
        File file = mock(File.class);
        when(fileService.getWeatherCamFile()).thenReturn(file);

        weatherCamService.uploadWeatherCam();

        verify(fileService, times(1)).getWeatherCamFile();
        verify(fileService, times(1)).getCreationTimeOfFile(file);
        verify(weatherHttpService, times(1)).putWeatherCam(any(WeatherCamDTO.class));
    }
}