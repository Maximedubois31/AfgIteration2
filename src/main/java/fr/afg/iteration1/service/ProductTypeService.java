package fr.afg.iteration1.service;

import java.util.List;

import fr.afg.iteration1.entity.ProductType;

public interface ProductTypeService {

    ProductType saveProductType(ProductType productType);

    List <ProductType> getAllProductType();
    
}
