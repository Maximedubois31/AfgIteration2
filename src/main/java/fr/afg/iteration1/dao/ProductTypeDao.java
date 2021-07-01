package fr.afg.iteration1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afg.iteration1.entity.ProductType;

/**
 * The interface Product type dao.
 */
public interface ProductTypeDao  extends JpaRepository<ProductType, Long> {
    
}
