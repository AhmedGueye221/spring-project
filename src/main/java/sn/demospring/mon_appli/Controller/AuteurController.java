package sn.demospring.mon_appli.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.demospring.mon_appli.Models.AuteurEntity;
import sn.demospring.mon_appli.Service.AuteurService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auteurs")
public class AuteurController {

    @Autowired
    private AuteurService auteurService;

    @PostMapping
    public AuteurEntity creerAuteur(@RequestBody AuteurEntity auteur) {
        return auteurService.creerAuteur(auteur);
    }

    @GetMapping
    public List<AuteurEntity> listerAuteurs() {
        return auteurService.listerAuteurs();
    }
       @GetMapping("/{id}")
    public ResponseEntity<?> getAuteurById(@PathVariable Long id) {
        try {
            AuteurEntity auteur = auteurService.getAuteurById(id);
            return ResponseEntity.ok(auteur);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Erreur lors de la récupération de l'auteur");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}