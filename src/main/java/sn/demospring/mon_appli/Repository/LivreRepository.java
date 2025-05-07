package sn.demospring.mon_appli.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.demospring.mon_appli.Models.LivreEntity;

public interface LivreRepository extends JpaRepository<LivreEntity, Long> {
    List<LivreEntity> findByAuteurId(Long id);
}
