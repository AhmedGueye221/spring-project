package sn.demospring.mon_appli.Models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class AuteurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String prenom;
    private String nom;
    private Integer age;
    
    /**
     * L'attribut cascade permet de propager les opérations de la classe AuteurEntity vers la classe LivreEntity.
     * Par exemple, si on supprime un auteur, tous les livres associés seront également supprimés.
     */
    @OneToMany(mappedBy = "auteur", cascade = CascadeType.ALL)
    private List<LivreEntity> livres;
}