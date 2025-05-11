package sn.demospring.mon_appli.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.demospring.mon_appli.Models.LivreEntity;
import sn.demospring.mon_appli.Models.AuteurEntity;
import sn.demospring.mon_appli.Repository.LivreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class LivreService {
    @Autowired
    private LivreRepository livreRepository;
    
    @Autowired
    private AuteurService auteurService;

    /**
     * Créer un nouveau livre
     */
    public LivreEntity creerLivre(LivreEntity livre) {
        if (livre.getAuteur() != null && livre.getAuteur().getId() != null) {
            AuteurEntity auteur = auteurService.getAuteurById(livre.getAuteur().getId());
            livre.setAuteur(auteur);
        }
        return livreRepository.save(livre);
    }

    /**
     * Lister tous les livres
     */
    public List<LivreEntity> listerLivres() {
        return livreRepository.findAll();
    }

    /**
     * Lister les livres avec pagination
     */
    public Page<LivreEntity> listerLivres(Pageable pageable) {
        return livreRepository.findAll(pageable);
    }

    /**
     * Lister les livres d'un auteur
     */
    public List<LivreEntity> listerLivresParAuteur(Long auteurId) {
        return livreRepository.findByAuteurId(auteurId);
    }

    /**
     * Modifier un livre
     */
    public LivreEntity modifierLivre(Long id, LivreEntity livre) {
        return livreRepository.findById(id)
            .map(existingLivre -> {
                if (livre.getTitre() != null) {
                    existingLivre.setTitre(livre.getTitre());
                }
                if (livre.getIsbn() != null) {
                    existingLivre.setIsbn(livre.getIsbn());
                }
                if (livre.getGenre() != null) {
                    existingLivre.setGenre(livre.getGenre());
                }
                if (livre.getDatePublication() != null) {
                    existingLivre.setDatePublication(livre.getDatePublication());
                }
                if (livre.getAuteur() != null && livre.getAuteur().getId() != null) {
                    AuteurEntity auteur = auteurService.getAuteurById(livre.getAuteur().getId());
                    existingLivre.setAuteur(auteur);
                }
                return livreRepository.save(existingLivre);
            })
            .orElseThrow(() -> new RuntimeException("Livre non trouvé avec l'id : " + id));
    }

    /**
     * Supprimer un livre
     */
    public void supprimerLivre(Long id) {
        livreRepository.deleteById(id);
    }

    /**
     * Récupérer un livre par son ID
     */
    public LivreEntity getLivreById(Long id) {
        return livreRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Livre non trouvé avec l'id : " + id));
    }
}