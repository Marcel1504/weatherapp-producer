package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather.calculation;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.entity.WeatherEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class WeatherCalculationServiceImplTest {

    @InjectMocks
    private WeatherCalculationService weatherCalculationService = new WeatherCalculationServiceImpl();

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(weatherCalculationService, "rainHitCounter", 4096);
        ReflectionTestUtils.setField(weatherCalculationService, "rainAmountPerHit", 295);
        ReflectionTestUtils.setField(weatherCalculationService, "acceptAsRainThresholdHits", 3);
        MockitoAnnotations.initMocks(this);
    }

    private List<WeatherEntity> setupWeatherEntityList() {
        List<WeatherEntity> entities = new ArrayList<>();
        entities.add(WeatherEntity.builder()
                .timestamp(Timestamp.valueOf("2020-01-01 20:00:00"))
                .rainHit(4088)
                .rainfall(0)
                .isRain(false)
                .id(1L)
                .wind(1.0)
                .temperature(1.0)
                .humidity(1)
                .build());
        entities.add(WeatherEntity.builder()
                .timestamp(Timestamp.valueOf("2020-01-01 20:00:00"))
                .rainHit(4093)
                .rainfall(0)
                .isRain(true)
                .id(1L)
                .wind(1.0)
                .temperature(1.0)
                .humidity(1)
                .build());
        entities.add(WeatherEntity.builder()
                .timestamp(Timestamp.valueOf("2020-01-01 20:00:00"))
                .rainHit(2)
                .rainfall(0)
                .isRain(true)
                .id(1L)
                .wind(1.0)
                .temperature(1.0)
                .humidity(1)
                .build());
        entities.add(WeatherEntity.builder()
                .timestamp(Timestamp.valueOf("2020-01-01 20:00:00"))
                .rainHit(7)
                .rainfall(0)
                .isRain(false)
                .id(1L)
                .wind(1.0)
                .temperature(1.0)
                .humidity(1)
                .build());
        entities.add(WeatherEntity.builder()
                .timestamp(Timestamp.valueOf("2020-01-01 20:00:00"))
                .rainHit(12)
                .rainfall(0)
                .isRain(false)
                .id(1L)
                .wind(1.0)
                .temperature(1.0)
                .humidity(1)
                .build());
        entities.add(WeatherEntity.builder()
                .timestamp(Timestamp.valueOf("2020-01-01 20:00:00"))
                .rainHit(14)
                .rainfall(0)
                .isRain(false)
                .id(1L)
                .wind(1.0)
                .temperature(1.0)
                .humidity(1)
                .build());
        entities.add(WeatherEntity.builder()
                .timestamp(Timestamp.valueOf("2020-01-01 20:00:00"))
                .rainHit(14)
                .rainfall(0)
                .isRain(false)
                .id(1L)
                .wind(1.0)
                .temperature(1.0)
                .humidity(1)
                .build());
        return entities;
    }

    @Test
    public void calculateRainfallTest() {
        List<WeatherEntity> result = weatherCalculationService.calculateRainfall(setupWeatherEntityList());
        assertEquals(0, result.get(0).getRainfall());
        assertEquals(1475, result.get(1).getRainfall());
        assertEquals(1475, result.get(2).getRainfall());
        assertEquals(1475, result.get(3).getRainfall());
        assertEquals(0, result.get(4).getRainfall());
        assertEquals(590, result.get(5).getRainfall());
        assertEquals(0, result.get(6).getRainfall());
    }
}