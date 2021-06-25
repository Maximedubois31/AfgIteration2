package fr.afg.iteration1.dao;

import fr.afg.iteration1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Long>{
    List<Product> findByProductIsActive(boolean isActive);
}
