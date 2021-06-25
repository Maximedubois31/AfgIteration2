package fr.afg.iteration1.controller;

import fr.afg.iteration1.entity.Product;
import fr.afg.iteration1.entity.ProductType;
import fr.afg.iteration1.service.CompanyService;
import fr.afg.iteration1.service.ProductService;
import fr.afg.iteration1.service.ProductTypeService;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@NoArgsConstructor
@SessionAttributes(value= {"idUser"})
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductTypeService productTypeService;

    @Autowired
    CompanyService companyService;

    @GetMapping("/products")
    public String listProducts(Model model) {

        model.addAttribute("products", productService.getAllProduct());
        return "products";
    }
    
    @GetMapping("/products/edit")
    public String productEdit(Model model, @RequestParam String id) {

        Product produit = productService.findById(Long.parseLong(id));
        List<ProductType> listCategories = productTypeService.getAllProductType();
        List<ProductType> listNewCategories = new ArrayList<>();
        for (ProductType productType : listCategories) {
            if (!productType.equals(produit.getProductType())) {
                listNewCategories.add(productType);
            }
        }

        model.addAttribute("product", produit);
        model.addAttribute("suppliers", companyService.getAllCompany());
        model.addAttribute("categories", productTypeService.getAllProductType());
        return "editProduct";
    }

   @PostMapping("/products/edit")
   public String postProductEdit(Model model, @ModelAttribute("product") Product product) {
                       
            productService.saveProduct(product);
        
            return "redirect:/products";
   } 

   @GetMapping("/products/delete")
   public String productDelete(Model model, @RequestParam String id) {


        //priceService.deleteByProductId(Long.parseLong(id));
        productService.deleteProduct(productService.findById(Long.parseLong(id)));

        return "redirect:/products";
   }

   @GetMapping("/products/details")
   public String productDetails(Model model, @RequestParam String id) {
       
       model.addAttribute("product",productService.findById(Long.parseLong(id)));

        return "detailsProduct";
   }
   
   
}
