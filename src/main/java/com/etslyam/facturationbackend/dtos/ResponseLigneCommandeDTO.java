package com.etslyam.facturationbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseLigneCommandeDTO {
    private String code;
    private Double prix;
    private Integer quantite;
    private ResponseProduitDTO responseProduitDTO;
}
