package sn.demospring.mon_appli.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sn.demospring.mon_appli.Models.AuteurEntity;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuteurServiceTest {

    @Autowired
    private AuteurService auteurService;

    @Test
    void testCreerAuteur() {
        AuteurEntity auteur = new AuteurEntity();
        auteur.setNom("Hugo");
        auteur.setPrenom("Victor");
        auteur.setAge(83);

        AuteurEntity savedAuteur = auteurService.creerAuteur(auteur);
        
        assertNotNull(savedAuteur.getId());
        assertEquals("Hugo", savedAuteur.getNom());
        assertEquals("Victor", savedAuteur.getPrenom());
    }
}