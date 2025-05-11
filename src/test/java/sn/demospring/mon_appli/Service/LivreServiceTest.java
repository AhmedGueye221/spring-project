package sn.demospring.mon_appli.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sn.demospring.mon_appli.Models.AuteurEntity;
import sn.demospring.mon_appli.Models.LivreEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LivreServiceTest {

    @Autowired
    private LivreService livreService;

    @Autowired
    private AuteurService auteurService;

    private AuteurEntity auteurTest;

    @BeforeEach
    void setUp() {
        // Créer un auteur pour les tests
        auteurTest = new AuteurEntity();
        auteurTest.setNom("Hugo");
        auteurTest.setPrenom("Victor");
        auteurTest.setAge(83);
        auteurTest = auteurService.creerAuteur(auteurTest);
    }

    @Test
    void testCreerLivre() {
        // Créer un livre
        LivreEntity livre = new LivreEntity();
        livre.setTitre("Les Misérables");
        livre.setIsbn("123-456-789");
        livre.setDatePublication(LocalDate.of(1862, 1, 1));
        livre.setGenre("Roman historique");
        livre.setAuteur(auteurTest);

        LivreEntity savedLivre = livreService.creerLivre(livre);
        
        assertNotNull(savedLivre.getId());
        assertEquals("Les Misérables", savedLivre.getTitre());
        assertEquals(auteurTest.getId(), savedLivre.getAuteur().getId());
    }

    @Test
    void testListerLivres() {
        // Créer plusieurs livres
        createLivre("Les Misérables", "123-456");
        createLivre("Notre-Dame de Paris", "456-789");
        
        List<LivreEntity> livres = livreService.listerLivres();
        
        assertFalse(livres.isEmpty());
        assertTrue(livres.size() >= 2);
    }

    @Test
    void testListerLivresParAuteur() {
        // Créer un livre pour l'auteur
        createLivre("Les Misérables", "123-456");
        
        List<LivreEntity> livresAuteur = livreService.listerLivresParAuteur(auteurTest.getId());
        
        assertFalse(livresAuteur.isEmpty());
        assertEquals(auteurTest.getId(), livresAuteur.get(0).getAuteur().getId());
    }

    @Test
    void testModifierLivre() {
        // Créer un livre puis le modifier
        LivreEntity livre = createLivre("Les Misérables", "123-456");
        
        livre.setTitre("Les Misérables - Édition revue");
        LivreEntity livreModifie = livreService.modifierLivre(livre.getId(), livre);
        
        assertEquals("Les Misérables - Édition revue", livreModifie.getTitre());
    }

    @Test
    void testSupprimerLivre() {
        // Créer un livre puis le supprimer
        LivreEntity livre = createLivre("Les Misérables", "123-456");
        Long livreId = livre.getId();
        
        livreService.supprimerLivre(livreId);
        
        List<LivreEntity> livres = livreService.listerLivres();
        assertTrue(livres.stream().noneMatch(l -> l.getId().equals(livreId)));
    }

    // Méthode utilitaire pour créer un livre
    private LivreEntity createLivre(String titre, String isbn) {
        LivreEntity livre = new LivreEntity();
        livre.setTitre(titre);
        livre.setIsbn(isbn);
        livre.setDatePublication(LocalDate.now());
        livre.setGenre("Roman");
        livre.setAuteur(auteurTest);
        return livreService.creerLivre(livre);
    }
}