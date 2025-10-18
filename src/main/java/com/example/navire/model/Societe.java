package com.example.navire.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "societes")
public class Societe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le nom de la société est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom de la société doit comporter entre 2 et 100 caractères")
    @Column(name = "nom", unique = true)
    private String nom;

    // Relation Many-to-Many avec Projet
    @ManyToMany(mappedBy = "societes")
    @ToString.Exclude
    private Set<Projet> projets;
}
