package com.etslyam.facturationbackend.inputs;

import com.etslyam.facturationbackend.dtos.RequestCommandeDTO;
import com.etslyam.facturationbackend.dtos.ResponseCommandeDTO;
import com.etslyam.facturationbackend.entities.Commande;
import com.etslyam.facturationbackend.utils.beans.Response;

import java.util.List;

public interface CommandeEvents {
    Response<ResponseCommandeDTO> findByCode(RequestCommandeDTO dto);
    Response<ResponseCommandeDTO> findByDeletedFalseAndArchivedFalseAndDisabledFalseAndActiveTrueAndPublicsTrue();
    Response<ResponseCommandeDTO> findByArchivedTrueAndActiveFalseAndPublicsFalse();
    Response<ResponseCommandeDTO> findByArchivedFalseAndActiveTrueAndPublicsTrue();
    Response<ResponseCommandeDTO> findByPayedTrue();
    Response<ResponseCommandeDTO> findByPayedFalse();
    Response<ResponseCommandeDTO> findByCreatedAt(RequestCommandeDTO dto);
    Response<ResponseCommandeDTO> save(RequestCommandeDTO dto);
    Response<ResponseCommandeDTO> delete(RequestCommandeDTO dto);
    Response<ResponseCommandeDTO> update(RequestCommandeDTO dto);
}
