package sn.demospring.mon_appli.Models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
/*
 * L'annotation @Data de Lombok génère automatiquement les méthodes getters et les setters pour la classe.
 */
@Data
public class LivreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String titre;
    private String isbn;
    private LocalDate datePublication;
    private String genre;
    
    @ManyToOne
    @JoinColumn(name = "auteur_id")
    private AuteurEntity auteur;
}