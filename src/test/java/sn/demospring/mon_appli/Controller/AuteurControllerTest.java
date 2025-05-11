package sn.demospring.mon_appli.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sn.demospring.mon_appli.Models.AuteurEntity;
import sn.demospring.mon_appli.Service.AuteurService;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuteurController.class)
public class AuteurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuteurService auteurService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreerAuteur() throws Exception {
        AuteurEntity auteur = new AuteurEntity();
        auteur.setNom("Hugo");
        auteur.setPrenom("Victor");
        auteur.setAge(83);

        when(auteurService.creerAuteur(any(AuteurEntity.class))).thenReturn(auteur);

        mockMvc.perform(post("/api/auteurs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(auteur)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Hugo"))
                .andExpect(jsonPath("$.prenom").value("Victor"))
                .andExpect(jsonPath("$.age").value(83));
    }

    @Test
    void testListerAuteurs() throws Exception {
        AuteurEntity auteur1 = new AuteurEntity();
        auteur1.setNom("Hugo");
        auteur1.setPrenom("Victor");

        AuteurEntity auteur2 = new AuteurEntity();
        auteur2.setNom("Camus");
        auteur2.setPrenom("Albert");

        when(auteurService.listerAuteurs()).thenReturn(Arrays.asList(auteur1, auteur2));

        mockMvc.perform(get("/api/auteurs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("Hugo"))
                .andExpect(jsonPath("$[1].nom").value("Camus"));
    }
}