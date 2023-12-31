package com.etslyam.facturationbackend.services;

import com.etslyam.facturationbackend.dtos.RequestLigneCommandeDTO;
import com.etslyam.facturationbackend.dtos.ResponseLigneCommandeDTO;
import com.etslyam.facturationbackend.inputs.LigneCommandeEvents;
import com.etslyam.facturationbackend.utils.beans.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LigneCommandeService implements LigneCommandeEvents {
    /**
     * @param dto
     * @return
     */
    @Override
    public Response<ResponseLigneCommandeDTO> findByCode(RequestLigneCommandeDTO dto) {
        return null;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public Response<ResponseLigneCommandeDTO> save(RequestLigneCommandeDTO dto) {
        return null;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public Response<ResponseLigneCommandeDTO> update(RequestLigneCommandeDTO dto) {
        return null;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public Response<ResponseLigneCommandeDTO> delete(RequestLigneCommandeDTO dto) {
        return null;
    }
}
