package fr.afg.iteration1.service;

import fr.afg.iteration1.entity.Product;
import fr.afg.iteration1.entity.User;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);
    List<Product> getAllProduct();
    Product findById(Long id);
	void deleteProduct(Product product);
    List<Product> findByProductIsActive(boolean isActive);
}
