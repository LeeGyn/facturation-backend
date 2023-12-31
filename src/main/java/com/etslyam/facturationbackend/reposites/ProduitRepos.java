package com.etslyam.facturationbackend.reposites;

import com.etslyam.facturationbackend.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepos extends JpaRepository<Produit,Long> {
    Produit findProduitById(Long id);
    Produit findProduitByCode(String code);
    Produit findProduitByDesignation(String designation);
    List<Produit> findProduitsByDeletedFalse();
    List<Produit> findProduitsByActiveFalse();
    List<Produit> findProduitsByArchivedFalse();
    List<Produit> findProduitsByPublicsTrue();
    List<Produit> findProduitsByDisabledFalse();
    List<Produit> findPoduitByDeletedFalseAndDisabledFalseAndArchivedFalseAndActiveTrueAndPublicsTrue();
}
