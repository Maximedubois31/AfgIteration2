package fr.afg.iteration1.controller;

import fr.afg.iteration1.entity.Product;
import fr.afg.iteration1.service.CompanyService;
import fr.afg.iteration1.service.ProductService;
import fr.afg.iteration1.service.ProductTypeService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * The type Product controller.
 */
@Controller
@NoArgsConstructor
@SessionAttributes(value = {"idUser"})
public class ProductController {

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

    /**
     * List products string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/products")
    public String listProducts(final Model model) {

        model.addAttribute("products", productService.getAllProduct());
        return "products";
    }

    /**
     * Product new string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/products/new")
    public String productNew(final Model model) {

        Product produit = new Product();

        model.addAttribute("product", produit);
        model.addAttribute("suppliers", companyService.getAllCompany());
        model.addAttribute("categories", productTypeService.getAllProductType());
        return "newProduct";
    }

    /**
     * Product edit string.
     *
     * @param model the model
     * @param id    the id
     * @return the string
     */
    @GetMapping("/products/edit")
    public String productEdit(final Model model, @RequestParam final String id) {

        Product produit = productService.findById(Long.parseLong(id));
        model.addAttribute("product", produit);
        model.addAttribute("suppliers", companyService.getAllCompany());
        model.addAttribute("categories", productTypeService.getAllProductType());
        return "editProduct";
    }

    /**
     * Post product edit string.
     *
     * @param model   the model
     * @param product the product
     * @return the string
     */
    @PostMapping("/products/edit")
    public String postProductEdit(final Model model, @ModelAttribute("product") final Product product) {

        productService.saveProduct(product);

        return "redirect:/products";
    }

    /**
     * Product delete string.
     *
     * @param model the model
     * @param id    the id
     * @return the string
     */
    @GetMapping("/products/delete")
    public String productDelete(final Model model, @RequestParam final String id) {


        //priceService.deleteByProductId(Long.parseLong(id));
        productService.deleteProduct(productService.findById(Long.parseLong(id)));

        return "redirect:/products";
    }

    /**
     * Product details string.
     *
     * @param model the model
     * @param id    the id
     * @return the string
     */
    @GetMapping("/products/details")
    public String productDetails(final Model model, @RequestParam final String id) {

        model.addAttribute("product", productService.findById(Long.parseLong(id)));

        return "detailsProduct";
    }


}
