package fr.afg.iteration1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afg.iteration1.dao.ProductTypeDao;
import fr.afg.iteration1.entity.ProductType;

/**
 * The type Product typeservice.
 */
@Service
@Transactional
public class ProductTypeserviceImpl implements ProductTypeService {

    /**
     * The Product type dao.
     */
    @Autowired
    ProductTypeDao productTypeDao;

    /**
     * The Product type service.
     */
    @Autowired
    ProductTypeService productTypeService;

    @Override
    public List<ProductType> getAllProductType() {
        return productTypeDao.findAll();
    }

    @Override
    public ProductType saveProductType(ProductType productType) {
        return productTypeDao.save(productType);
    }

    @Override
    public ProductType findById(Long id) {
        return productTypeDao.getById(id);
    }

    
}
