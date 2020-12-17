package com.example.findapartmentbackend.mapper;

import com.example.findapartmentbackend.model.Ad;
import io.tej.SwaggerCodgen.model.AdItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface AdMapper {
//    @Mapping(source = "status", target = "status")
//    @Mapping(source = "price", target = "price")
//    @Mapping(source = "name", target = "name")
    //@Mapping(source = "apartment", target = "apartment")
    //@Mapping(source = "id", target = "id")
    AdItem  adToAddItem(Ad ad);
}
