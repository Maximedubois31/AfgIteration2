package fr.afg.iteration1.dao;

import fr.afg.iteration1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Long>{

}
