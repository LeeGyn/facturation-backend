package com.etslyam.facturationbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCommandeDTO {
    private String code;
    private Double prix;
    private Integer quantite;
    private List<ResponseLigneCommandeDTO> responseLigneCommandeDTOS = new ArrayList<>();

    private boolean deleted;
    private boolean active;
    private boolean archived;
    private boolean publics;
    private boolean disabled;
    private String createdAt;
}
