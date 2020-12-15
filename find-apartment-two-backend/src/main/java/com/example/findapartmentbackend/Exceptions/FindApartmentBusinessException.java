package com.example.findapartmentbackend.Exceptions;

import lombok.Data;

/**
 * This class represents a business exception for FindApartment application.
 */
@Data
public class FindApartmentBusinessException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;

    public FindApartmentBusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

}
