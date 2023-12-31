package com.etslyam.facturationbackend.inputs;

import com.etslyam.facturationbackend.dtos.RequestProduitDTO;
import com.etslyam.facturationbackend.dtos.ResponseProduitDTO;
import com.etslyam.facturationbackend.entities.Produit;
import com.etslyam.facturationbackend.utils.beans.Response;


public interface ProduitsEvents {
    Response<ResponseProduitDTO> save(RequestProduitDTO dto);
    Response<ResponseProduitDTO> update(RequestProduitDTO dto);
    Response<ResponseProduitDTO> delete(RequestProduitDTO dto);
    Response<ResponseProduitDTO> findProduitById(RequestProduitDTO dto);
    Response<ResponseProduitDTO> findProduitByCode(RequestProduitDTO dto);
    Response<ResponseProduitDTO> findProduitByDesignation(RequestProduitDTO dto);
    Response<ResponseProduitDTO> findProduitsByDeletedFalse();
    Response<ResponseProduitDTO> findProduitsByActiveFalse();
    Response<ResponseProduitDTO> findProduitsByArchivedFalse();
    Response<ResponseProduitDTO> findProduitsByPublicsTrue();
    Response<ResponseProduitDTO> findProduitsByDisabledFalse();
    Response<ResponseProduitDTO> findPoduitByDeletedFalseAndDisabledFalseAndArchivedFalseAndActiveTrueAndPublicsTrue();

}
