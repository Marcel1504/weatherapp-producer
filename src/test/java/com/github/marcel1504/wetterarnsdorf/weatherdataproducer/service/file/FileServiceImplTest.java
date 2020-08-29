package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.service.file;

import com.github.marcel1504.wetterarnsdorf.weatherdataproducer.exception.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class FileServiceImplTest {

    @InjectMocks
    private FileService fileService = new FileServiceImpl();

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(fileService, "weatherDataFilePath", "datapath");
        ReflectionTestUtils.setField(fileService, "weatherCamFilePath", "campath");
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getFileTestFail() {
        assertThrows(ServiceException.class, () -> fileService.getFile("test"));
        assertThrows(ServiceException.class, () -> fileService.getWeatherCamFile());
        assertThrows(ServiceException.class, () -> fileService.getWeatherDataFile());
        assertThrows(ServiceException.class, () -> fileService.getCreationTimeOfFile(null));
    }

}