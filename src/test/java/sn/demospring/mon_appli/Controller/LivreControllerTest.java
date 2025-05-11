package sn.demospring.mon_appli.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sn.demospring.mon_appli.Models.AuteurEntity;
import sn.demospring.mon_appli.Models.LivreEntity;
import sn.demospring.mon_appli.Service.LivreService;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LivreController.class)
public class LivreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LivreService livreService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreerLivre() throws Exception {
        AuteurEntity auteur = new AuteurEntity();
        auteur.setId(1L);
        
        LivreEntity livre = new LivreEntity();
        livre.setTitre("Les Misérables");
        livre.setIsbn("123-456");
        livre.setDatePublication(LocalDate.now());
        livre.setGenre("Roman");
        livre.setAuteur(auteur);

        when(livreService.creerLivre(any(LivreEntity.class))).thenReturn(livre);

        mockMvc.perform(post("/api/livres")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(livre)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titre").value("Les Misérables"));
    }

    @Test
    void testListerLivres() throws Exception {
        LivreEntity livre1 = new LivreEntity();
        livre1.setTitre("Les Misérables");
        
        LivreEntity livre2 = new LivreEntity();
        livre2.setTitre("Notre-Dame de Paris");

        when(livreService.listerLivres()).thenReturn(Arrays.asList(livre1, livre2));

        mockMvc.perform(get("/api/livres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titre").value("Les Misérables"))
                .andExpect(jsonPath("$[1].titre").value("Notre-Dame de Paris"));
    }

    @Test
    void testListerLivresParAuteur() throws Exception {
        LivreEntity livre = new LivreEntity();
        livre.setTitre("Les Misérables");

        when(livreService.listerLivresParAuteur(1L)).thenReturn(Arrays.asList(livre));

        mockMvc.perform(get("/api/livres/auteur/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titre").value("Les Misérables"));
    }

    @Test
    void testModifierLivre() throws Exception {
        LivreEntity livre = new LivreEntity();
        livre.setTitre("Les Misérables - Édition revue");

        when(livreService.modifierLivre(eq(1L), any(LivreEntity.class))).thenReturn(livre);

        mockMvc.perform(put("/api/livres/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(livre)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titre").value("Les Misérables - Édition revue"));
    }

    @Test
    void testSupprimerLivre() throws Exception {
        mockMvc.perform(delete("/api/livres/1"))
                .andExpect(status().isOk());
    }
}