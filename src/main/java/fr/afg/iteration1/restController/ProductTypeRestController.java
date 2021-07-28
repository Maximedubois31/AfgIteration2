package fr.afg.iteration1.restController;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.afg.iteration1.dtoResponse.ProductTypeResponse;
import fr.afg.iteration1.entity.ProductType;
import fr.afg.iteration1.service.ProductTypeService;

@RestController
@CrossOrigin
@RequestMapping(value="/admin", headers="Accept=application/json")
public class ProductTypeRestController {
 
    /**
     * The Product type service.
     */
    @Autowired
    ProductTypeService productTypeService;

    private static final Gson gson = new Gson();


    /**
     * List products types Json.
     *
     * @return json
     */
    @GetMapping("/productsTypes")
    public ResponseEntity<?> getAllProductType() {

        try {
            List<ProductType> productsTypes = productTypeService.getAllProductType();
            List<ProductTypeResponse> response = new LinkedList<>();
            ProductTypeResponse productTypeResponse = null;

            for (ProductType productType : productsTypes) {
                productTypeResponse = new ProductTypeResponse(productType);
                BeanUtils.copyProperties(productType, productTypeResponse);
                // productResponse.setName(product.getName());

                response.add(productTypeResponse);

            }

            return new ResponseEntity<List<ProductTypeResponse>>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(gson.toJson("La recherche n'a pas abouti. Problèmes possibles : identifiant"),
                    HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * products types detail Json.
     *
     * @return json
     */
    @GetMapping("/productType/{id}")
    public ResponseEntity<?> getOneProductType(@PathVariable("id") Long id) {

        try {
            ProductType productType = productTypeService.findById(id);

            ProductTypeResponse response = new ProductTypeResponse(productType);
            BeanUtils.copyProperties(productType, response);

            return new ResponseEntity<ProductTypeResponse>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(gson.toJson("La recherche n'a pas abouti."),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
