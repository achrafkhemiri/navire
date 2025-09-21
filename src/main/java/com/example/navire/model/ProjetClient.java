
package com.example.navire.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "projet_client")
public class ProjetClient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private Double quantiteAutorisee;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Projet getProjet() { return projet; }
    public void setProjet(Projet projet) { this.projet = projet; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public Double getQuantiteAutorisee() { return quantiteAutorisee; }
    public void setQuantiteAutorisee(Double quantiteAutorisee) { this.quantiteAutorisee = quantiteAutorisee; }
}
