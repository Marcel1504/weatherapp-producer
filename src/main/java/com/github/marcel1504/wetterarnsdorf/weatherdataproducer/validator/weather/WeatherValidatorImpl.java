package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.validator.weather;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.entity.WeatherEntity;
import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherValidatorImpl implements WeatherValidator {

    @Override
    public void validate(List<WeatherEntity> entities) {
        entities.forEach(this::validate);
    }

    @Override
    public void validate(WeatherEntity entity) {
        if (entity == null ||
                entity.getTimestamp() == null ||
                entity.getTemperature() == null ||
                entity.getHumidity() == null ||
                entity.getRainHit() == null ||
                entity.getIsRain() == null ||
                entity.getWind() == null ||
                entity.getRainfall() == null
        ) {
            throw new ServiceException("Weather data is not valid");
        }
    }
}
