package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceImplTest {

    @InjectMocks
    private WeatherService weatherService = new WeatherServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
}