package fr.afg.iteration1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import fr.afg.iteration1.service.ProductService;
import fr.afg.iteration1.service.ProductTypeService;

/**
 * The type Dashboard controller.
 */
@Controller
public class DashboardController {

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
     * Dashboard string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("dashboard")
    public String dashboard(Model model) {
        return "dashboard";
    }

    /**
     * Crud user string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/cruduser")
    public String crudUser(Model model) {
        return null;
    }

    /**
     * Crud product string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/crudProduct")
    public String crudProduct(Model model) {
        return null;
    }

}
