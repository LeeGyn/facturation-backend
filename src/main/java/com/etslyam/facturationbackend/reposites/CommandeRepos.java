package com.etslyam.facturationbackend.reposites;

import com.etslyam.facturationbackend.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepos extends JpaRepository<Commande, Long> {
    Commande findByCode(String code);
    List<Commande> findByDeletedFalseAndArchivedFalseAndDisabledFalseAndActiveTrueAndPublicsTrue();
    List<Commande> findByArchivedTrueAndActiveFalseAndPublicsFalse();
    List<Commande> findByArchivedFalseAndActiveTrueAndPublicsTrue();
    List<Commande> findByPayedTrue();
    List<Commande> findByPayedFalse();
    List<Commande> findByCreatedAt(String createdAt);

}
