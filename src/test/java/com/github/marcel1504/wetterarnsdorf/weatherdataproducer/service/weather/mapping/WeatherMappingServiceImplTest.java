package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather.mapping;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.dto.weather.WeatherDTO;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.entity.WeatherEntity;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.exception.ServiceException;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.file.FileService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class WeatherMappingServiceImplTest {

    @Mock
    private FileService fileService;

    @Mock
    private Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder;

    @InjectMocks
    private WeatherMappingService weatherMappingService = new WeatherMappingServiceImpl();

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
    public void mapFromWeatherFileToEntityTestFail() {
        assertThrows(ServiceException.class, () -> weatherMappingService.mapFromWeatherFileToEntity());
    }

    @Test
    public void mapFromWeatherEntityToWeatherDTOTest() {
        List<WeatherDTO> list = weatherMappingService.mapFromWeatherEntityToWeatherDTO(setupWeatherEntityList());
        assertEquals(1.0, list.get(0).getTemperature());
    }
}