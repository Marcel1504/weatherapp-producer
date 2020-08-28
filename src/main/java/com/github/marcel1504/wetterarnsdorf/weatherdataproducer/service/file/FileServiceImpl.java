package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.file;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    @Value("${weatherdataproducer.inputFilePath.weatherData}")
    private String weatherDataFilePath;

    @Value("${weatherdataproducer.inputFilePath.weatherCam}")
    private String weatherCamFilePath;

    @Override
    public File getWeatherDataFile() {
        return getFile(weatherDataFilePath);
    }

    @Override
    public File getWeatherCamFile() {
        return getFile(weatherCamFilePath);
    }

    @Override
    public File getFile(String absoluteFilePath) {
        if (!Files.exists(Paths.get(absoluteFilePath))) {
            throw new ServiceException(String.format("file %s does not exist", absoluteFilePath));
        }
        return new File(absoluteFilePath);
    }


}
