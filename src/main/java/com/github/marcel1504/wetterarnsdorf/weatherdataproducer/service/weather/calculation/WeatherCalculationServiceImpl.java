package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.weather.calculation;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.entity.WeatherEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class WeatherCalculationServiceImpl implements WeatherCalculationService {

    @Value("${weatherdataproducer.calculation.rain.hitCounterMax}")
    private int rainHitCounter;

    @Value("${weatherdataproducer.calculation.rain.amountPerHit}")
    private int rainAmountPerHit;

    @Value("${weatherdataproducer.calculation.rain.acceptAsRainThresholdHits}")
    private int acceptAsRainThresholdHits;

    @Override
    public List<WeatherEntity> calculateRainfall(List<WeatherEntity> entities) {
        List<WeatherEntity> newList = new ArrayList<>(entities);
        newList.sort(Comparator.comparing(WeatherEntity::getTimestamp));

        for (int i = 0; i < newList.size(); i++) {
            //skip first entity
            if (i == 0) continue;

            //get possible entities
            WeatherEntity prev = newList.get(i - 1);
            WeatherEntity curr = newList.get(i);
            WeatherEntity next = null;
            if (i < newList.size() - 1) {
                next = newList.get(i + 1);
            }

            //calculate rainHit difference
            int diff;
            int currHit = curr.getRainHit();
            int prevHit = prev.getRainHit();
            if (curr.getRainHit() < prev.getRainHit()) {
                diff = (rainHitCounter + currHit) - prevHit;
            } else {
                diff = currHit - prevHit;
            }

            //check if data can be accepted as rain
            boolean acceptAsRain = curr.getIsRain() ||
                    prev.getIsRain() ||
                    diff <= acceptAsRainThresholdHits ||
                    (next != null && next.getIsRain());

            if (acceptAsRain) {
                curr.setRainfall(diff * rainAmountPerHit);
            }
        }

        return newList;
    }
}
