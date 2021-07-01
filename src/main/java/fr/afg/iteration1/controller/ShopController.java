package fr.afg.iteration1.controller;

import fr.afg.iteration1.entity.*;
import fr.afg.iteration1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Shop controller.
 */
@Controller
public class ShopController {

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
     * The Purchase order service.
     */
    @Autowired
    PurchaseOrderService purchaseOrderService;

    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    /**
     * List products string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/shop")
    public String listProducts(final Model model) {

        model.addAttribute("newSearch", new Search());
        model.addAttribute("types", productTypeService.getAllProductType());
        model.addAttribute("filtre", new Filtre());
        model.addAttribute("commandLine", new CommandLine());
        model.addAttribute("products", productService.findByProductIsActive(true));

        return "shop";
    }

    /**
     * Post list products string.
     *
     * @param model  the model
     * @param search the search
     * @param filtre the filtre
     * @return the string
     */
    @PostMapping("/shop")
    public String postListProducts(final Model model,
                                   @ModelAttribute("newSearch") final Search search,
                                   @ModelAttribute("filtre") final Filtre filtre) {

        List<Product> products = productService.findByProductIsActive(true);
        List<Product> filterProducts = new ArrayList<>();

        if (search.getSearchText() != null) {
            products = search.searchForName(products);
        }

        if (filtre.getFiltres() != null) {
            for (ProductType type : filtre.getFiltres()) {

                filterProducts.addAll(products.stream()
                                                .filter(c -> c.getProductType() == type)
                                                .collect(Collectors.toList())
                );
            }
        } else {
            filterProducts = products;
        }

        model.addAttribute("types", productTypeService.getAllProductType());
        model.addAttribute("products", filterProducts);
        model.addAttribute("filtre", filtre);
        model.addAttribute("commandLine", new CommandLine());

        return "shop";
    }

    /**
     * Add to purchase order string.
     *
     * @param model       the model
     * @param commandLine the command line
     * @param session     the session
     * @return the string
     */
    @PostMapping("/addToPurchaseOrder")
    public String addToPurchaseOrder(final Model model,
                                     @ModelAttribute("commandLine") final CommandLine commandLine,
                                     final HttpSession session) {

        PurchaseOrder purchaseOrder = (PurchaseOrder) session.getAttribute("purchaseOrder");
        
        LocalTime now = LocalTime.now();
        //time limit for tomorrow delivery
        LocalTime timeLimitOrders = LocalTime.parse("12:00:00");
        
        if (now.compareTo(timeLimitOrders) > 0) {
            purchaseOrder.setDeliveryDate(LocalDate.now().plusDays(2L));
        } else {
            purchaseOrder.setDeliveryDate(LocalDate.now().plusDays(1L));
        }
        
        
        for (CommandLine line : purchaseOrder.getLines()) {
            if (line.getProduct().getId().equals(commandLine.getProduct().getId())) {
                line.setDesiredQuantity(commandLine.getDesiredQuantity() + line.getDesiredQuantity());
                session.setAttribute("purchaseOrder", purchaseOrder);
                return "redirect:shop";
            }
        }
        commandLine.setActivePrice(commandLine.getProduct().getLowPrice());
        purchaseOrderService.addCommandLine(purchaseOrder, commandLine);
        session.setAttribute("purchaseOrder", purchaseOrder);

        return "redirect:shop";
    }


}
