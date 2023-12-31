package com.etslyam.facturationbackend.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String code;
    @Column(unique = true)
    private String designation;
    private Double prix;
    private Integer quantite;
    private byte[] photo;

    private boolean deleted = false;
    private boolean active = true;
    private boolean archived = false;
    private boolean publics = true;
    private boolean disabled = false;
    private String createdAt;
}
