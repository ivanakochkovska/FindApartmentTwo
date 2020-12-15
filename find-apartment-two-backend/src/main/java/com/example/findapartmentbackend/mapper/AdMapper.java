package com.example.findapartmentbackend.mapper;

import com.example.findapartmentbackend.model.Ad;
import io.tej.SwaggerCodgen.model.AdItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AdMapper {
    List<AdItem> adToAddItem(List<Ad> ads);
}
