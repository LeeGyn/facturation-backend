package com.etslyam.facturationbackend.services;

import com.etslyam.facturationbackend.dtos.RequestCommandeDTO;
import com.etslyam.facturationbackend.dtos.RequestLigneCommandeDTO;
import com.etslyam.facturationbackend.dtos.ResponseCommandeDTO;
import com.etslyam.facturationbackend.dtos.ResponseProduitDTO;
import com.etslyam.facturationbackend.entities.Commande;
import com.etslyam.facturationbackend.entities.LigneCommande;
import com.etslyam.facturationbackend.entities.Produit;
import com.etslyam.facturationbackend.inputs.CommandeEvents;
import com.etslyam.facturationbackend.mappers.CommandeMapper;
import com.etslyam.facturationbackend.mappers.LigneCommandeMapper;
import com.etslyam.facturationbackend.reposites.CommandeRepos;
import com.etslyam.facturationbackend.reposites.LigneCommandeRepos;
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

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class CommandeService implements CommandeEvents {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CommandeRepos commandeRepos;
    private final ProduitRepos produitRepos;
    private final CommandeMapper commandeMapper;
    private final LigneCommandeRepos ligneCommandeRepos;
    private final LigneCommandeMapper ligneCommandeMapper;

    public CommandeService(CommandeRepos commandeRepos, ProduitRepos produitRepos, CommandeMapper commandeMapper, LigneCommandeRepos ligneCommandeRepos, LigneCommandeMapper ligneCommandeMapper) {
        this.commandeRepos = commandeRepos;
        this.produitRepos = produitRepos;
        this.commandeMapper = commandeMapper;
        this.ligneCommandeRepos = ligneCommandeRepos;
        this.ligneCommandeMapper = ligneCommandeMapper;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public Response<ResponseCommandeDTO> findByCode(RequestCommandeDTO dto) {
        Response<ResponseCommandeDTO> _response = null;

        try {

            Commande commande = commandeRepos.findByCode(dto.getCode());

            if (commande != null) {
                _response = ResponseImpl.<ResponseCommandeDTO>builder().status(Status.SUCCESS)
                        .data(commandeMapper.toResponse(commande))
                        .message(Messages.SUCCESS)
                        .hasError(false)
                        .build();
            }else {
                _response = ResponseImpl.<ResponseCommandeDTO>builder().status(Status.NOT_FOUND)
                        .message(Messages.NOT_FOUND)
                        .hasError(false)
                        .build();
            }

        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
            _response = ResponseImpl.<ResponseCommandeDTO>builder().status(Status.INTERNAL_ERROR)
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
    public Response<ResponseCommandeDTO> findByDeletedFalseAndArchivedFalseAndDisabledFalseAndActiveTrueAndPublicsTrue() {
        Response<ResponseCommandeDTO> _response = null;
        List<ResponseCommandeDTO> responseCommandeDTOList = new ArrayList<>();

        try {

            List<Commande> commandeList = commandeRepos.findByDeletedFalseAndArchivedFalseAndDisabledFalseAndActiveTrueAndPublicsTrue();

            if (!commandeList.isEmpty()) {
                responseCommandeDTOList = commandeList.stream().map(commandeMapper::toResponse).toList();
                _response = ResponseImpl.<ResponseCommandeDTO>builder().status(Status.SUCCESS)
                        .datas(responseCommandeDTOList)
                        .message(Messages.SUCCESS)
                        .hasError(false)
                        .build();
            }else {
                _response = ResponseImpl.<ResponseCommandeDTO>builder().status(Status.DATA_EMPTY)
                        .message(Messages.DATA_EMPTY)
                        .hasError(false)
                        .build();
            }

        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
            _response = ResponseImpl.<ResponseCommandeDTO>builder().status(Status.INTERNAL_ERROR)
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
    public Response<ResponseCommandeDTO> findByArchivedTrueAndActiveFalseAndPublicsFalse() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public Response<ResponseCommandeDTO> findByArchivedFalseAndActiveTrueAndPublicsTrue() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public Response<ResponseCommandeDTO> findByPayedTrue() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public Response<ResponseCommandeDTO> findByPayedFalse() {
        return null;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public Response<ResponseCommandeDTO> findByCreatedAt(RequestCommandeDTO dto) {
        return null;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public Response<ResponseCommandeDTO> save(RequestCommandeDTO dto) {
        Response<ResponseCommandeDTO> _response = null;

        try {
            double prixTotal = 0;
            int quantiteTotal = 0;

            // Génération du code de la commande
            String codeCommande = CodeGenerator.generateUniqueCode();

            // Création d'une nouvelle commande
            Commande commande = new Commande();
            commande.setCode(codeCommande);
            commande.setCreatedAt(Instant.now().toString());

            // Sauvegarde de la commande dans la base de données
            Commande savedCommande = commandeRepos.save(commande);

            for (RequestLigneCommandeDTO requestLigneCommandeDTO : dto.getRequestLigneCommandeDTOS()) {
                Produit produit = produitRepos.findProduitByCode(requestLigneCommandeDTO.getCode());

                if (produit != null) {
                    // Mise à jour de la quantité du produit
                    produit.setQuantite(produit.getQuantite() - 1);
                    produitRepos.save(produit);

                    // Calcul du prix total et de la quantité totale
                    prixTotal += produit.getPrix();
                    quantiteTotal += 1;

                    // Création d'une nouvelle ligne de commande
                    LigneCommande ligneCommande = new LigneCommande();
                    ligneCommande.setCode(CodeGenerator.generateUniqueCode());
                    ligneCommande.setProduit(produit);
                    ligneCommande.setPrix(produit.getPrix());
                    ligneCommande.setQuantite(requestLigneCommandeDTO.getQuantite());
                    ligneCommande.setCreatedAt(Instant.now().toString());

                    // Sauvegarde de la ligne de commande dans la base de données
                    LigneCommande savedLigneCommande = ligneCommandeRepos.save(ligneCommande);

                    // Ajout de la ligne de commande à la commande
                    savedCommande.getLigneCommandes().add(savedLigneCommande);
                }
            }

            // Mise à jour de la commande avec les totaux
            savedCommande.setPayed(true);
            savedCommande.setPrix(prixTotal);
            savedCommande.setQuantite(quantiteTotal);
            commandeRepos.save(savedCommande);

            _response = ResponseImpl.<ResponseCommandeDTO>builder().status(Status.SUCCESS)
                    .message(Messages.SUCCESS)
                    .hasError(false)
                    .build();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            _response = ResponseImpl.<ResponseCommandeDTO>builder().status(Status.INTERNAL_ERROR)
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
    public Response<ResponseCommandeDTO> delete(RequestCommandeDTO dto) {
        Response<ResponseCommandeDTO> _response = null;

        try {
            Commande commande = commandeRepos.findByCode(dto.getCode());
            commande.setActive(false);
            commande.setDeleted(true);
            commande.setPublics(false);

            for (LigneCommande ligneCommande : commande.getLigneCommandes()){
                ligneCommande.setActive(false);
                ligneCommande.setDeleted(true);
                ligneCommande.setPublics(false);
                ligneCommandeRepos.save(ligneCommande);
            }

            commandeRepos.save(commande);

            _response = ResponseImpl.<ResponseCommandeDTO>builder().status(Status.SUCCESS)
                    .message(Messages.SUCCESS)
                    .hasError(false)
                    .build();

        } catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage(), e);
            _response = ResponseImpl.<ResponseCommandeDTO>builder().status(Status.INTERNAL_ERROR)
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
    public Response<ResponseCommandeDTO> update(RequestCommandeDTO dto) {
        return null;
    }
}
