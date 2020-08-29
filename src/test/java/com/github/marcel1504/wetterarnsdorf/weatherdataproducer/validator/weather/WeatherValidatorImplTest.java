package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.validator.weather;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.entity.WeatherEntity;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.exception.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class WeatherValidatorImplTest {

    @InjectMocks
    private WeatherValidator weatherValidator = new WeatherValidatorImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private List<WeatherEntity> setupWeatherEntityList() {
        List<WeatherEntity> list = new ArrayList<>();
        list.add(setupWeatherEntity());
        return list;
    }

    private List<WeatherEntity> setupInvalidWeatherEntityList() {
        List<WeatherEntity> list = new ArrayList<>();
        list.add(setupInvalidWeatherEntity());
        return list;
    }

    private WeatherEntity setupWeatherEntity() {
        return WeatherEntity.builder()
                .temperature(1.0)
                .humidity(1)
                .rainHit(1)
                .isRain(false)
                .rainfall(1)
                .wind(1.0)
                .timestamp(Timestamp.valueOf("2020-01-01 12:00:00"))
                .build();
    }

    private WeatherEntity setupInvalidWeatherEntity() {
        return WeatherEntity.builder().build();
    }

    @Test
    public void validateTest() {
        assertDoesNotThrow(() -> weatherValidator.validate(setupWeatherEntity()));
        assertDoesNotThrow(() -> weatherValidator.validate(setupWeatherEntityList()));
    }

    @Test
    public void validateTestFail() {
        assertThrows(ServiceException.class, () -> weatherValidator.validate(setupInvalidWeatherEntity()));
        assertThrows(ServiceException.class, () -> weatherValidator.validate(setupInvalidWeatherEntityList()));
    }
}