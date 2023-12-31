package com.etslyam.facturationbackend.inputs;

import com.etslyam.facturationbackend.dtos.RequestLigneCommandeDTO;
import com.etslyam.facturationbackend.dtos.RequestProduitDTO;
import com.etslyam.facturationbackend.dtos.ResponseLigneCommandeDTO;
import com.etslyam.facturationbackend.entities.LigneCommande;
import com.etslyam.facturationbackend.utils.beans.Response;

public interface LigneCommandeEvents {
    Response<ResponseLigneCommandeDTO> findByCode(RequestLigneCommandeDTO dto);
    Response<ResponseLigneCommandeDTO> save(RequestLigneCommandeDTO dto);
    Response<ResponseLigneCommandeDTO> update(RequestLigneCommandeDTO dto);
    Response<ResponseLigneCommandeDTO> delete(RequestLigneCommandeDTO dto);
}
