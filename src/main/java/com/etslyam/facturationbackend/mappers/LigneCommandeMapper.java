package com.etslyam.facturationbackend.mappers;

import com.etslyam.facturationbackend.dtos.RequestLigneCommandeDTO;
import com.etslyam.facturationbackend.dtos.ResponseLigneCommandeDTO;
import com.etslyam.facturationbackend.entities.LigneCommande;
import com.etslyam.facturationbackend.entities.Produit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LigneCommandeMapper {
    ResponseLigneCommandeDTO toLigneCommandeDTO(LigneCommande ligneCommande);

    LigneCommande toLigneCommande(RequestLigneCommandeDTO requestLigneCommandeDTO);

    default Produit mapProduit(Produit produit) {
        if (produit == null) {
            return null;
        }

        return produit;
    }
}
