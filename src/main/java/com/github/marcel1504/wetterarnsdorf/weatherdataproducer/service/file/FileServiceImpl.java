package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.file;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class FileServiceImpl implements FileService {

    @Value("${weatherdataproducer.inputFilePath.weatherCam}")
    private String weatherCamFilePath;

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

    @Override
    public LocalDateTime getCreationTimeOfFile(File file) {
        BasicFileAttributes attributes;
        try {
            attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            FileTime fileTime = attributes.creationTime();
            return LocalDateTime.ofInstant(fileTime.toInstant(), ZoneId.systemDefault());
        } catch (Exception e) {
            throw new ServiceException("Cannot read file attributes");
        }
    }


}
