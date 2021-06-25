package fr.afg.iteration1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afg.iteration1.entity.ProductType;

public interface ProductTypeDao  extends JpaRepository<ProductType, Long>{
    
}
