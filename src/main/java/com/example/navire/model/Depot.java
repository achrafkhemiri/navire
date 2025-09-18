package com.example.navire.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;
import com.example.navire.model.Projet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "depots")
public class Depot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le nom du dépôt est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom du dépôt doit comporter entre 2 et 100 caractères")
    @Column(nullable = false, unique = true)
    private String nom;

    // Relation Depot <-> Projet (Many-to-Many)
    @ManyToMany(mappedBy = "depots")
    private Set<Projet> projets;
}
