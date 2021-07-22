package fr.afg.iteration1.service;

import java.util.List;

import fr.afg.iteration1.entity.ProductType;

/**
 * The interface Product type service.
 */
public interface ProductTypeService {

    /**
     * Save product type product type.
     *
     * @param productType the product type
     * @return the product type
     */
    ProductType saveProductType(ProductType productType);

    /**
     * Gets all product type.
     *
     * @return the all product type
     */
    List <ProductType> getAllProductType();

    /**
     * Gets product type by ID.
     *
     * @return product type
     */
    ProductType findById(Long id);
    
}
