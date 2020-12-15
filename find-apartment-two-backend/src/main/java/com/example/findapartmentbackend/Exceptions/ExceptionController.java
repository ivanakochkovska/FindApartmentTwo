package com.example.findapartmentbackend.Exceptions;

import com.example.findapartmentbackend.mapper.EnumMapper;
import io.tej.SwaggerCodgen.model.ErrorDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * This controller represents a controller for handling exceptions.
 */
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionController extends ResponseEntityExceptionHandler {
    ErrorDetails errorDetails;
    private final EnumMapper enumMapper;

    @ExceptionHandler(FindApartmentBusinessException.class)
    protected ResponseEntity<Object> handleBusinessException(FindApartmentBusinessException ex) {
        errorDetails = new ErrorDetails();
        errorDetails.setErrorMessage(ex.getMessage());
        errorDetails.setErrorCode(enumMapper.mapEnum(ex.getErrorCode()));
        return new ResponseEntity<>( errorDetails,HttpStatus.BAD_REQUEST);
    }
}
