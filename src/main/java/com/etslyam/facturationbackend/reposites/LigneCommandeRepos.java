package com.etslyam.facturationbackend.reposites;

import com.etslyam.facturationbackend.entities.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeRepos extends JpaRepository<LigneCommande,Long> {
    LigneCommande findByCode(String code);
}
