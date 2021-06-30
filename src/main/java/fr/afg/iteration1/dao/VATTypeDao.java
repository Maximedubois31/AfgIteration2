package fr.afg.iteration1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afg.iteration1.entity.VATType;

public interface VATTypeDao extends JpaRepository<VATType, Long> {

}
