package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.http.weather;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.dto.weather.WeatherDTO;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.dto.weather.cam.WeatherCamDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WeatherHttpServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherHttpService weatherHttpService = new WeatherHttpServiceImpl();

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(weatherHttpService, "locationKey", "testlocation");
        MockitoAnnotations.initMocks(this);
    }

    private List<WeatherDTO> setupWeatherDTOList() {
        List<WeatherDTO> list = new ArrayList<>();
        list.add(WeatherDTO.builder()
                .timestamp(LocalDateTime.parse("2020-01-01T12:00:00"))
                .build());
        return list;
    }

    private WeatherCamDTO setupWeatherCamDTO() {
        return WeatherCamDTO.builder()
                .file(mock(FileSystemResource.class))
                .createdTimestamp(LocalDateTime.parse("2020-01-01T12:00:00"))
                .build();
    }

    @Test
    public void putWeatherDataSyncTest() {
        List<WeatherDTO> list = setupWeatherDTOList();
        weatherHttpService.putWeatherDataSync(list);
        verify(restTemplate, times(1)).put("/weather/synchronize?locationKey={l}", list, "testlocation");
    }

    @Test
    public void putWeatherCamTest() {
        WeatherCamDTO dto = setupWeatherCamDTO();
        weatherHttpService.putWeatherCam(dto);
        verify(restTemplate, times(1)).put(eq("/weathercam"), any());
    }
}