package com.etslyam.facturationbackend.mappers;

import com.etslyam.facturationbackend.dtos.RequestCommandeDTO;
import com.etslyam.facturationbackend.dtos.ResponseCommandeDTO;
import com.etslyam.facturationbackend.entities.Commande;
import com.etslyam.facturationbackend.entities.LigneCommande;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommandeMapper {
    ResponseCommandeDTO toResponse(Commande commande);

    Commande toCommande(RequestCommandeDTO requestCommandeDTO);

    default LigneCommande mapLigneCommande(LigneCommande ligneCommande) {
        if (ligneCommande == null) {
            return null;
        }
        return ligneCommande;
    }
}
