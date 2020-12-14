package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather.mapping;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.dto.weather.WeatherDTO;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.entity.WeatherEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class WeatherMappingServiceImplTest {

    @InjectMocks
    private WeatherMappingService weatherMappingService = new WeatherMappingServiceImpl();

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(weatherMappingService, "rainMultiplier", 10.0);
        ReflectionTestUtils.setField(weatherMappingService, "windMultiplier", 1.0);
        MockitoAnnotations.initMocks(this);
    }

    private List<WeatherEntity> setupWeatherEntityList() {
        List<WeatherEntity> list = new ArrayList<>();
        list.add(WeatherEntity.builder()
                .dateTime(1L)
                .temperature(1.0)
                .humidity(1)
                .rainfall(1.0)
                .wind(1.0)
                .build());
        return list;
    }

    @Test
    public void mapFromWeatherEntityToWeatherDTOTest() {
        List<WeatherDTO> list = weatherMappingService.mapFromWeatherEntityToWeatherDTO(setupWeatherEntityList());
        assertEquals(1.0, list.get(0).getTemperature());
        assertEquals(10.0, list.get(0).getRainfall());
        assertEquals(1.0, list.get(0).getWind());
    }
}