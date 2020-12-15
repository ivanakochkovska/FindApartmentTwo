package com.example.findapartmentbackend.mapper;

import com.example.findapartmentbackend.Exceptions.ErrorCode;
import io.tej.SwaggerCodgen.model.ErrorDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface EnumMapper {
    @Mapping(source = "USER_NOT_FOUND", target = "USER_NOT_FOUND")
    @Mapping(source = "USER_ALREADY_EXISTS",target = "USER_ALREADY_EXISTS")
    @Mapping(source = "PROVIDE_PERSONAL_INFORMATION",target = "PROVIDE_PERSONAL_INFORMATION")
    ErrorDetails.ErrorCodeEnum mapEnum(ErrorCode errorCode);
}
