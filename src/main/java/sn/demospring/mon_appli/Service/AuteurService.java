package sn.demospring.mon_appli.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.demospring.mon_appli.Models.AuteurEntity;
import sn.demospring.mon_appli.Repository.AuteurRepository;

@Service
public class AuteurService {
    @Autowired
    private AuteurRepository auteurRepository;

    public AuteurEntity creerAuteur(AuteurEntity auteur) {
        return auteurRepository.save(auteur);
    }

    public List<AuteurEntity> listerAuteurs() {
        return auteurRepository.findAll();
    }

    public AuteurEntity getAuteurById(Long id) {
        return auteurRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Auteur non trouvé avec l'id : " + id));
    }
}