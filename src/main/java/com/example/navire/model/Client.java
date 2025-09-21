package com.example.navire.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;
import com.example.navire.model.ProjetClient;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le numéro client est obligatoire")
    @Size(min = 2, max = 20, message = "Le numéro client doit comporter entre 2 et 20 caractères")
    @Column(nullable = false, unique = true)
    private String numero;

    @NotNull(message = "Le nom du client est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom du client doit comporter entre 2 et 100 caractères")
    @Column(nullable = false)
    private String nom;


    // Relation Client <-> Projet (Many-to-Many via ProjetClient)
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProjetClient> projetClients;
}
