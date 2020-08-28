package com.github.marcel1504.wetterarnsdorf.weatherdataproducer.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ServiceException extends RuntimeException {
    private final String message;
}
