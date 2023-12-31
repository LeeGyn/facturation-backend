package com.etslyam.facturationbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestProduitDTO {
    private String code;
    private String designation;
    private Double prix;
    private Integer quantite;
    private String photo;

    private boolean deleted;
    private boolean active;
    private boolean archived;
    private boolean publics;
    private boolean disabled;
    private String createdAt;
}
