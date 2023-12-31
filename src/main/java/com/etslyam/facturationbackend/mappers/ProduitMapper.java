package com.etslyam.facturationbackend.mappers;

import com.etslyam.facturationbackend.dtos.ResponseProduitDTO;
import com.etslyam.facturationbackend.entities.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Base64;

@Mapper(componentModel = "spring")
public interface ProduitMapper {
    @Mappings({
            @Mapping(target = "photo", source = "photo")
    })
    ResponseProduitDTO toResponseDto(Produit produit);

    @Mappings({
            @Mapping(target = "photo", source = "photo")
    })
    Produit toProduit(ResponseProduitDTO responseProduitDTO);

    default String byteArrayToString(byte[] bytes) {
        if (bytes == null) {
            // Handle the case where src is null, e.g., return an empty string or throw an exception
            return "";
        }
        return Base64.getEncoder().encodeToString(bytes);
    }

    default byte[] stringToByteArray(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }

}
