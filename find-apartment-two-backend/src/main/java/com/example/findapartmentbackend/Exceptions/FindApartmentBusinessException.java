package com.example.findapartmentbackend.Exceptions;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * This class represents a business exception for FindApartment application.
 */
@Data
@Slf4j
public class FindApartmentBusinessException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;

    public FindApartmentBusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
        log.warn("FindApartmentBusinessException occured");
    }

}
