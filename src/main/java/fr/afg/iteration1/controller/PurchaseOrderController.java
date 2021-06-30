package fr.afg.iteration1.controller;

import javax.servlet.http.HttpSession;

import fr.afg.iteration1.entity.User;
import fr.afg.iteration1.service.PurchaseOrderService;
import fr.afg.iteration1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import fr.afg.iteration1.entity.CommandLine;
import fr.afg.iteration1.entity.PurchaseOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Purchase order controller.
 */
@Controller
public class PurchaseOrderController {

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
     * Gets purchase order.
     *
     * @param model   the model
     * @param session the session
     * @return the purchase order
     */
    @GetMapping("/purchaseorder")
    public String getPurchaseOrder(Model model, HttpSession session) {
        PurchaseOrder purchaseOrder = (PurchaseOrder) session.getAttribute("purchaseOrder");
        Float total = 0f;
        for (CommandLine line : purchaseOrder.getLines()) {
            total = total + line.getActivePrice() * line.getProduct().getMoq() * line.getDesiredQuantity();
        }
        model.addAttribute("total", total);
        return "purchaseorder";
    }

    /**
     * Validate purchase order string.
     *
     * @param model   the model
     * @param session the session
     * @return the string
     */
    @PostMapping("validatePurchaseOrder")
    public String validatePurchaseOrder(Model model, HttpSession session) {
        PurchaseOrder purchaseOrder = (PurchaseOrder) session.getAttribute("purchaseOrder");
        purchaseOrder.setDeliveryDate(purchaseOrder.getCreationDate().plusDays(1L));
        purchaseOrderService.savePurchaseOrder(purchaseOrder);
        User user = userService.getUserById((Long) session.getAttribute("idUser"));
        purchaseOrder = new PurchaseOrder();
        purchaseOrder.setCreator(user);
        purchaseOrder.setCreationDate(LocalDate.now());
        List<CommandLine> commandLines = new ArrayList<CommandLine>();
        purchaseOrder.setLines(commandLines);
        session.setAttribute("purchaseOrder", purchaseOrder);
        return "redirect:shop";
    }

    /**
     * Delete command line string.
     *
     * @param session the session
     * @param id      the id
     * @return the string
     */
    @GetMapping("deleteCommandLine")
    public String deleteCommandLine(HttpSession session, Long id) {
        PurchaseOrder purchaseOrder = (PurchaseOrder) session.getAttribute("purchaseOrder");
        CommandLine lineToDelete = new CommandLine();
        for (CommandLine line : purchaseOrder.getLines()) {
            if (line.getProduct().getId().equals(id)) {
                System.out.println("line" + line.getId());
                lineToDelete = line;
            }
        }
        purchaseOrder.getLines().remove(lineToDelete);
        session.setAttribute("purchaseOrder", purchaseOrder);
        return "redirect:purchaseorder";
    }

    /**
     * Modif command line string.
     *
     * @param session         the session
     * @param productId       the product id
     * @param desiredQuantity the desired quantity
     * @return the string
     */
    @PostMapping("modifCommandLine")
    public String modifCommandLine(HttpSession session, Long productId, Float desiredQuantity) {
        PurchaseOrder purchaseOrder = (PurchaseOrder) session.getAttribute("purchaseOrder");
        CommandLine lineToDelete = new CommandLine();
        CommandLine lineToUpdate = new CommandLine();
        for (CommandLine line : purchaseOrder.getLines()) {
            if (line.getProduct().getId().equals(productId)) {
                lineToDelete = line;
                lineToUpdate = line;
                lineToUpdate.setDesiredQuantity(desiredQuantity);
            }
        }
        purchaseOrder.getLines().remove(lineToDelete);
        purchaseOrder.getLines().add(lineToUpdate);
        session.setAttribute("purchaseOrder", purchaseOrder);
        return "redirect:purchaseorder";
    }

}
