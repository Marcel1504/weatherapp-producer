package com.github.marcel1504.weatherapp.producer.service.file;

import java.io.File;
import java.time.LocalDateTime;

public interface FileService {
    File getWeatherCamFile();
    File getFile(String absoluteFilePath);
    LocalDateTime getCreationTimeOfFile(File file);
}
