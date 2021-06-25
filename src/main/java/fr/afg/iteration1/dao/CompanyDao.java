package fr.afg.iteration1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afg.iteration1.entity.Company;

public interface CompanyDao extends JpaRepository<Company, Long>{
    
}
