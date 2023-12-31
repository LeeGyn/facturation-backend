package com.etslyam.facturationbackend.services;

import com.etslyam.facturationbackend.dtos.RequestProduitDTO;
import com.etslyam.facturationbackend.dtos.ResponseProduitDTO;
import com.etslyam.facturationbackend.entities.Produit;
import com.etslyam.facturationbackend.inputs.ProduitsEvents;
import com.etslyam.facturationbackend.mappers.ProduitMapper;
import com.etslyam.facturationbackend.reposites.ProduitRepos;
import com.etslyam.facturationbackend.utils.CodeGenerator;
import com.etslyam.facturationbackend.utils.beans.Messages;
import com.etslyam.facturationbackend.utils.beans.Response;
import com.etslyam.facturationbackend.utils.beans.ResponseImpl;
import com.etslyam.facturationbackend.utils.beans.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@Transactional
public class ProduitService implements ProduitsEvents {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProduitRepos produitRepos;
    private final ProduitMapper produitMapper;

    public ProduitService(ProduitRepos produitRepos, ProduitMapper produitMapper) {
        this.produitRepos = produitRepos;
        this.produitMapper = produitMapper;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public Response<ResponseProduitDTO> save(RequestProduitDTO dto) {
        Response<ResponseProduitDTO> _response = null;

        try {
            System.out.println(dto.getPhoto().toString());
            // Convertir une chaîne Base64 en tableau de bytes
            byte[] photoBytes = Base64.getDecoder().decode(dto.getPhoto().getBytes(StandardCharsets.UTF_8));
            // Convertir un tableau de bytes en chaîne Base64
            //String photoBase64 = Base64.getEncoder().encodeToString(photoBytes);

            Produit p  = Produit.builder()
                    .designation(dto.getDesignation())
                    .code(CodeGenerator.generateUniqueCode())
                    .photo(photoBytes)
                    .prix(dto.getPrix())
                    .quantite(dto.getQuantite())
                    .active(true)
                    .publics(true)
                    .archived(false)
                    .deleted(false)
                    .disabled(false)
                    .createdAt(Instant.now().toString())
                    .build();

            Produit p2 = produitRepos.save(p);

            _response = ResponseImpl.<ResponseProduitDTO>builder().status(Status.SUCCESS)
                    .data(produitMapper.toResponseDto(p2))
                    .message(Messages.SUCCESS)
                    .hasError(false)
                    .build();

        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
            _response = ResponseImpl.<ResponseProduitDTO>builder().status(Status.INTERNAL_ERROR)
                    .message(Messages.INTERNAL_ERROR)
                    .hasError(true)
                    .build();
        }

        return _response;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public Response<ResponseProduitDTO> update(RequestProduitDTO dto) {
        Response<ResponseProduitDTO> _response = null;

        try {
            Produit produit = produitRepos.findProduitByCode(dto.getCode());
            // Convertir une chaîne Base64 en tableau de bytes
            byte[] photoBytes = Base64.getDecoder().decode(dto.getPhoto());

            produit.setDesignation(dto.getDesignation() == null ? produit.getDesignation() : null);
            produit.setPrix(dto.getPrix() == null ? produit.getPrix() : 0);
            produit.setQuantite(dto.getQuantite() == null ? produit.getQuantite() : 0);
            produit.setPhoto(photoBytes);

            Produit p2 = produitRepos.save(produit);

            _response = ResponseImpl.<ResponseProduitDTO>builder().status(Status.SUCCESS)
                    .data(produitMapper.toResponseDto(p2))
                    .message(Messages.SUCCESS)
                    .hasError(false)
                    .build();

        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
            _response = ResponseImpl.<ResponseProduitDTO>builder().status(Status.INTERNAL_ERROR)
                    .message(Messages.INTERNAL_ERROR)
                    .hasError(true)
                    .build();
        }

        return _response;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public Response<ResponseProduitDTO> delete(RequestProduitDTO dto) {
        Response<ResponseProduitDTO> _response = null;

        try {
            Produit produit = produitRepos.findProduitByCode(dto.getCode());
            produit.setActive(false);
            produit.setDeleted(true);
            produit.setPublics(false);

            Produit p2 = produitRepos.save(produit);

            _response = ResponseImpl.<ResponseProduitDTO>builder().status(Status.SUCCESS)
                    .data(produitMapper.toResponseDto(p2))
                    .message(Messages.SUCCESS)
                    .hasError(false)
                    .build();

        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
            _response = ResponseImpl.<ResponseProduitDTO>builder().status(Status.INTERNAL_ERROR)
                    .message(Messages.INTERNAL_ERROR)
                    .hasError(true)
                    .build();
        }

        return _response;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public Response<ResponseProduitDTO> findProduitById(RequestProduitDTO dto) {
        return null;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public Response<ResponseProduitDTO> findProduitByCode(RequestProduitDTO dto) {
        Response<ResponseProduitDTO> _response = null;

        try {

            Produit produit = produitRepos.findProduitByCode(dto.getCode());

            if (produit != null) {
                _response = ResponseImpl.<ResponseProduitDTO>builder().status(Status.SUCCESS)
                        .data(produitMapper.toResponseDto(produit))
                        .message(Messages.SUCCESS)
                        .hasError(false)
                        .build();
            }else {
                _response = ResponseImpl.<ResponseProduitDTO>builder().status(Status.SUCCESS)
                        .data(produitMapper.toResponseDto(produit))
                        .message(Messages.SUCCESS)
                        .hasError(false)
                        .build();
            }

        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
            _response = ResponseImpl.<ResponseProduitDTO>builder().status(Status.INTERNAL_ERROR)
                    .message(Messages.INTERNAL_ERROR)
                    .hasError(true)
                    .build();
        }

        return _response;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public Response<ResponseProduitDTO> findProduitByDesignation(RequestProduitDTO dto) {
        Response<ResponseProduitDTO> _response = null;

        try {

            Produit produit = produitRepos.findProduitByDesignation(dto.getDesignation());

            if (produit != null) {
                _response = ResponseImpl.<ResponseProduitDTO>builder().status(Status.SUCCESS)
                        .data(produitMapper.toResponseDto(produit))
                        .message(Messages.SUCCESS)
                        .hasError(false)
                        .build();
            }else {
                _response = ResponseImpl.<ResponseProduitDTO>builder().status(Status.SUCCESS)
                        .data(produitMapper.toResponseDto(produit))
                        .message(Messages.SUCCESS)
                        .hasError(false)
                        .build();
            }

        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
            _response = ResponseImpl.<ResponseProduitDTO>builder().status(Status.INTERNAL_ERROR)
                    .message(Messages.INTERNAL_ERROR)
                    .hasError(true)
                    .build();
        }

        return _response;
    }

    /**
     * @return
     */
    @Override
    public Response<ResponseProduitDTO> findProduitsByDeletedFalse() {
        Response<ResponseProduitDTO> _response = null;
        List<ResponseProduitDTO> responseProduitDTOList = new ArrayList<>();

        try {

            List<Produit> produitList = produitRepos.findProduitsByDeletedFalse();

            if (!produitList.isEmpty()) {
                responseProduitDTOList = produitList.stream().map(produitMapper::toResponseDto).toList();
                _response = ResponseImpl.<ResponseProduitDTO>builder().status(Status.SUCCESS)
                        .datas(responseProduitDTOList)
                        .message(Messages.SUCCESS)
                        .hasError(false)
                        .build();
            }else {
                _response = ResponseImpl.<ResponseProduitDTO>builder().status(Status.DATA_EMPTY)
                        .message(Messages.DATA_EMPTY)
                        .hasError(false)
                        .build();
            }

        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
            _response = ResponseImpl.<ResponseProduitDTO>builder().status(Status.INTERNAL_ERROR)
                    .message(Messages.INTERNAL_ERROR)
                    .hasError(true)
                    .build();
        }

        return _response;
    }

    /**
     * @return
     */
    @Override
    public Response<ResponseProduitDTO> findProduitsByActiveFalse() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public Response<ResponseProduitDTO> findProduitsByArchivedFalse() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public Response<ResponseProduitDTO> findProduitsByPublicsTrue() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public Response<ResponseProduitDTO> findProduitsByDisabledFalse() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public Response<ResponseProduitDTO> findPoduitByDeletedFalseAndDisabledFalseAndArchivedFalseAndActiveTrueAndPublicsTrue() {
        return null;
    }
}
