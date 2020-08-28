package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.file;

import java.io.File;

public interface FileService {
    File getWeatherDataFile();
    File getWeatherCamFile();
    File getFile(String absoluteFilePath);
}
