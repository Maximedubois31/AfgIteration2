package fr.afg.iteration1.controller;


import fr.afg.iteration1.entity.CommandLine;
import fr.afg.iteration1.entity.Filtre;
import fr.afg.iteration1.entity.PurchaseOrder;
import fr.afg.iteration1.entity.User;
import fr.afg.iteration1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;

/**
 * The type Order controller.
 */
@SessionAttributes(value = {"order"})
@Controller
public class OrderController {

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
     * The Excel service.
     */
    @Autowired
    ExcelService excelService;

    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    /**
     * List orderes string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/orderes")
    public String listOrderes(Model model) {

        model.addAttribute("newSearch", new Search());
        model.addAttribute("types", productTypeService.getAllProductType());
        model.addAttribute("orderes", purchaseOrderService.getAllOrderes());
        model.addAttribute("filtre", new Filtre());

        return "orderes";
    }

    /**
     * Gets order.
     *
     * @param model the model
     * @param idPo  the id po
     * @return the order
     */
    @GetMapping("/to-orderpreparator")
    public String getOrder(Model model,
                           @RequestParam("idPo") Long idPo) {

        PurchaseOrder order = purchaseOrderService.getPoById(idPo);
        model.addAttribute("order", order);
        return "orderpreparator";
    }

    /**
     * Gets selected order.
     *
     * @param model   the model
     * @param session the session
     * @return the selected order
     */
    @GetMapping("/to-orderselectedpreparator")
    public String getSelectedOrder(Model model,
                                   HttpSession session) {

        PurchaseOrder order = (PurchaseOrder) session.getAttribute("order");
        model.addAttribute("order", order);
        Float total = 0f;
        for (CommandLine line : order.getLines()) {
            if (line.getOrderedQuantity() != null) {
                total = total
                        + line.getActivePrice()
                        * line.getProduct().getMoq()
                        * line.getOrderedQuantity();
            }
        }
        model.addAttribute("total", total);
        return "orderselectedpreparator";
    }

    /**
     * Update ordered quantity string.
     *
     * @param session         the session
     * @param productId       the product id
     * @param orderedQuantity the ordered quantity
     * @param model           the model
     * @return the string
     */
    @PostMapping("updateQuantity")
    public String updateOrderedQuantity(HttpSession session,
                                        Long productId,
                                        Float orderedQuantity,
                                        Model model) {

        PurchaseOrder order = (PurchaseOrder) session.getAttribute("order");
        int index = 0;
        for (CommandLine line : order.getLines()) {
            if (line.getProduct().getId().equals(productId)) {
                order.getLines().get(index).setOrderedQuantity(orderedQuantity);
            }
            index++;
        }

        session.setAttribute("order", order);
        model.addAttribute("order", order);
        return "redirect:to-orderselectedpreparator";
    }

    /**
     * Validate selected order string.
     *
     * @param model   the model
     * @param session the session
     * @return the string
     * @throws IOException the io exception
     */
    @PostMapping("validateSelectedOrder")
    public String validateSelectedOrder(Model model,
                                        HttpSession session) throws IOException {
        PurchaseOrder order = (PurchaseOrder) session.getAttribute("order");
        excelService.creerExcel(session, order);
        User user = userService.getUserById((Long) session.getAttribute("idUser"));
        order.setPreparator(user);
        order.setPreparationDate(LocalDate.now());
        purchaseOrderService.savePurchaseOrder(order);
        session.removeAttribute("order");
        return "redirect:orderes";
    }

}