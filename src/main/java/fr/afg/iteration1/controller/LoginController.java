package fr.afg.iteration1.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import fr.afg.iteration1.entity.CommandLine;
import fr.afg.iteration1.entity.PurchaseOrder;
import fr.afg.iteration1.entity.User;
import fr.afg.iteration1.service.ProductService;
import fr.afg.iteration1.service.PurchaseOrderService;
import fr.afg.iteration1.service.UserService;
import lombok.NoArgsConstructor;

/**
 * The type Login controller.
 */
@Controller
@NoArgsConstructor
@SessionAttributes(value = {"idUser", "purchaseOrder"})
public class LoginController {

    /**
     * The Product service.
     */
    @Autowired
    ProductService productService;

    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    /**
     * The Purchase order service.
     */
    @Autowired
    PurchaseOrderService purchaseOrderService;


    /**
     * Do after login string.
     *
     * @param model  the model
     * @param idUser the id user
     * @return the string
     */
    @PreAuthorize("hasRole('CUSTOMER') " +
                    "|| hasRole('PREPARATOR') " +
                    "|| hasRole('LOGISTIC')")
    @RequestMapping(value = "/afterlogin")
    public String doAfterLogin(final Model model,
                               @RequestParam(name = "idUser", required = false) Long idUser) {

        model.addAttribute("products", productService.getAllProduct());
        if (idUser == null) {
            Object principal = SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getPrincipal();
            if (principal instanceof UserDetails) {
                String idString = ((UserDetails) principal).getUsername();
                idUser = Long.parseLong(idString);
            }
            model.addAttribute("idUser", idUser);
            User user = userService.getUserById(idUser);
            //Creer panier
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setCreator(user);
            purchaseOrder.setCreationDate(LocalDate.now());
            List<CommandLine> commandLines = new ArrayList<CommandLine>();
            purchaseOrder.setLines(commandLines);
            //Mettre panier en session et en bdd
            model.addAttribute("purchaseOrder", purchaseOrder);
            if(user.getRoles().equals("ROLE_PREPARATOR")) {
                return "redirect:orderes";
            }
            System.out.println(user.getRoles());
        }

        return "redirect:shop";
    }

}
