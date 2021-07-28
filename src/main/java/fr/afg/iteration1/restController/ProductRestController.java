package fr.afg.iteration1.restController;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.afg.iteration1.dtoRequest.ProductRequest;
import fr.afg.iteration1.dtoResponse.ProductResponse;
import fr.afg.iteration1.entity.Product;
import fr.afg.iteration1.service.CompanyService;
import fr.afg.iteration1.service.ProductService;
import fr.afg.iteration1.service.ProductTypeService;

@RestController
@CrossOrigin
@RequestMapping(value = "/admin", headers = "Accept=application/json")
public class ProductRestController {

    /**
     * The Product service.
     */
    @Autowired
    ProductService productService;

    /**
     * The Product type service.
     */
    @Autowired
    ProductTypeService productTypeService;

    /**
     * The Company service.
     */
    @Autowired
    CompanyService companyService;

    private static final Gson gson = new Gson();

    /**
     * List products Json.
     *
     * @return json
     */
    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {

        try {
            List<Product> products = productService.getAllProduct();
            List<ProductResponse> response = new LinkedList<>();
            ProductResponse productResponse = null;

            for (Product product : products) {
                productResponse = new ProductResponse(product);
                BeanUtils.copyProperties(product, productResponse);
                // productResponse.setName(product.getName());
                response.add(productResponse);

            }

            return new ResponseEntity<List<ProductResponse>>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(gson.toJson("La recherche n'a pas abouti. Problèmes possibles : identifiant, prix "),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Product details Json.
     *
     * @param id the id
     * @return json
     */
    @GetMapping("/product/details/{id}")
    public ResponseEntity<?> productDetails(@PathVariable("id") Long id) {

        try {
            Product product = productService.findById(id);
            
            ProductResponse response = new ProductResponse(product);
            BeanUtils.copyProperties(product, response);

            return new ResponseEntity<ProductResponse>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(gson.toJson("La recherche n'a pas abouti."),
                    HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Post product create Json.
     *
     * @param product the product
     * @return Json
     */
    @PostMapping("/product/new")
    public ResponseEntity<?> newProduct(@RequestBody ProductRequest product) {

        try {

            Product newProduct = new Product();

            //SET data in newProduct
            newProduct.setName(product.getName());
            newProduct.setRef(product.getRef());
            newProduct.setBrand(product.getBrand());
            newProduct.setImageUrl(product.getImageUrl());
            newProduct.setOrigin(product.getOrigin());
            newProduct.setQuantityUnity(product.getQuantityUnity());
            newProduct.setDescription(product.getDescription());
            newProduct.setMoq(product.getMoq());
            newProduct.setProductIsActive(product.getProductIsActive());
            newProduct.setLowPrice(product.getLowPrice());
            newProduct.setHighPrice(product.getHighPrice());
            newProduct.setProductType(productTypeService.findById(product.getProductTypeId()));
            //newProduct.setDescription();
            newProduct.setSupplier(companyService.findByIdCompany(product.getSupplierId()));
            newProduct.setName(product.getName());

            //Save
            Product productAdd = productService.saveProduct(newProduct);
            
            //SET response
            ProductResponse response = new ProductResponse(productAdd);
            BeanUtils.copyProperties(productAdd, response);

            return new ResponseEntity<ProductResponse>(response, HttpStatus.CREATED);

        } catch (Exception e) {
           
            e.printStackTrace();
            return new ResponseEntity<String>(gson.toJson("Impossible d'enregistrer les données"), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Put product edit Json.
     *
     * @param product the product
     * @return Json
     */
    @PutMapping("/product/edit")
    public ResponseEntity<?> productEdit(@RequestBody ProductRequest product) {

        try {
            Product newProduct = new Product();

            //SET data in newProduct
            newProduct.setId(product.getId());
            newProduct.setName(product.getName());
            newProduct.setRef(product.getRef());
            newProduct.setBrand(product.getBrand());
            newProduct.setImageUrl(product.getImageUrl());
            newProduct.setOrigin(product.getOrigin());
            newProduct.setQuantityUnity(product.getQuantityUnity());
            newProduct.setDescription(product.getDescription());
            newProduct.setMoq(product.getMoq());
            newProduct.setProductIsActive(product.getProductIsActive());
            newProduct.setLowPrice(product.getLowPrice());
            newProduct.setHighPrice(product.getHighPrice());
            newProduct.setProductType(productTypeService.findById(product.getProductTypeId()));
            //newProduct.setDescription();
            newProduct.setSupplier(companyService.findByIdCompany(product.getSupplierId()));
            newProduct.setName(product.getName());

            //Save
            Product productAdd = productService.saveProduct(newProduct);
            
            //SET response
            ProductResponse response = new ProductResponse(productAdd);
            BeanUtils.copyProperties(productAdd, response);

            return new ResponseEntity<ProductResponse>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(gson.toJson("Impossible d'enregistrer les données"), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Product delete Json.
     *
     * @param id the id
     * @return the Json
     */
    @DeleteMapping("product/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {

        try {
            System.out.println(id);
            Product product = productService.findById(id);
            productService.deleteProduct(product);

            return new ResponseEntity<String>(gson.toJson("Deleted"), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(gson.toJson("Impossible de supprimer les données"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
