package fr.afg.iteration1.service;

import fr.afg.iteration1.entity.Product;

import java.util.List;

/**
 * The interface Product service.
 */
public interface ProductService {

    /**
     * Save product product.
     *
     * @param product the product
     * @return the product
     */
    Product saveProduct(Product product);

    /**
     * Gets all product.
     *
     * @return the all product
     */
    List<Product> getAllProduct();

    /**
     * Find by id product.
     *
     * @param id the id
     * @return the product
     */
    Product findById(Long id);

    /**
     * Delete product.
     *
     * @param product the product
     */
    void deleteProduct(Product product);

    /**
     * Find by product is active list.
     *
     * @param isActive the is active
     * @return the list
     */
    List<Product> findByProductIsActive(boolean isActive);
}
