package com.example.findapartmentbackend.mapper;

import com.example.findapartmentbackend.model.AdditionalInformationEnum;
import io.tej.SwaggerCodgen.model.AdItemApartment;
import org.mapstruct.Mapper;

@Mapper
public interface AdditionaInformationMapper {
    AdditionalInformationEnum mapAdditionalInformation(AdItemApartment.AdditionalInformationEnum entity);
}
