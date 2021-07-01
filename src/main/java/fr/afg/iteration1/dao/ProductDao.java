package fr.afg.iteration1.dao;

import fr.afg.iteration1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Product dao.
 */
public interface ProductDao extends JpaRepository<Product, Long> {
    /**
     * Find by product is active list.
     *
     * @param isActive the is active
     * @return the list
     */
    List<Product> findByProductIsActive(boolean isActive);
}
