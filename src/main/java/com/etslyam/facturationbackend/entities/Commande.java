package com.etslyam.facturationbackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String code;
    private Double prix;
    private Integer quantite;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "commandes_ligneCommandes",
            joinColumns = { @JoinColumn(name = "commande_id") },
            inverseJoinColumns = { @JoinColumn(name = "ligneCommande_id") },
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"commande_id","ligneCommande_id"})
            }
    )
    private List<LigneCommande> ligneCommandes = new ArrayList<>();
    @ElementCollection
    private List<String> requestedLigneCommandes=new ArrayList<>();

    private boolean deleted = false;
    private boolean active = true;
    private boolean archived = false;
    private boolean publics = true;
    private boolean disabled = false;
    private boolean payed = false;
    private String createdAt;
}
