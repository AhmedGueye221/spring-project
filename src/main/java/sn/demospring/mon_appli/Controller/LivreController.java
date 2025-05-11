package sn.demospring.mon_appli.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.demospring.mon_appli.Models.LivreEntity;
import sn.demospring.mon_appli.Service.LivreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/livres")
public class LivreController {

    @Autowired
    private LivreService livreService;

  @PostMapping
public ResponseEntity<?> creerLivre(@RequestBody LivreEntity livre) {
    try {
        LivreEntity nouveauLivre = livreService.creerLivre(livre);
        return ResponseEntity.ok(nouveauLivre);
    } catch (Exception e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "Erreur lors de la création du livre");
        errorResponse.put("error", e.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}

    @GetMapping
    public Page<LivreEntity> listerLivres(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return livreService.listerLivres(PageRequest.of(page, size));
    }

    @GetMapping("/auteur/{auteurId}")
    public List<LivreEntity> listerLivresParAuteur(@PathVariable Long auteurId) {
        return livreService.listerLivresParAuteur(auteurId);
    }

     @PutMapping("/{id}")
    public ResponseEntity<?> modifierLivre(@PathVariable Long id, @RequestBody LivreEntity livre) {
        try {
            LivreEntity livreModifie = livreService.modifierLivre(id, livre);
            return ResponseEntity.ok(livreModifie);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Erreur lors de la modification du livre");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

       @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerLivre(@PathVariable Long id) {
        try {
            livreService.supprimerLivre(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Livre supprimé avec succès");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Erreur lors de la suppression du livre");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}