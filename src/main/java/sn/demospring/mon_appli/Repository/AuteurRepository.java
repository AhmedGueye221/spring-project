package sn.demospring.mon_appli.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.demospring.mon_appli.Models.AuteurEntity;

public interface AuteurRepository extends JpaRepository<AuteurEntity, Long> {
    
}
