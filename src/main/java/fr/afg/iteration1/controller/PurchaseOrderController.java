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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalTime;
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
    public String getPurchaseOrder(final Model model, final HttpSession session) {
        PurchaseOrder purchaseOrder = (PurchaseOrder) session.getAttribute("purchaseOrder");
        Float total = 0f;
        LocalDate deliveryDate = purchaseOrder.getDeliveryDate();
        
        for (CommandLine line : purchaseOrder.getLines()) {
            total = total + line.getActivePrice() * line.getProduct().getMoq() * line.getDesiredQuantity();
        }
        
        model.addAttribute("total", total);
        model.addAttribute("deliveryDate", deliveryDate);
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
    public String validatePurchaseOrder(final Model model, final HttpSession session) {
        PurchaseOrder purchaseOrder = (PurchaseOrder) session.getAttribute("purchaseOrder");

        LocalTime now = LocalTime.now();
        //time limit for tomorrow delivery
        LocalTime timeLimitOrders = LocalTime.parse("12:00:00");
        
        if (now.compareTo(timeLimitOrders) > 0) {
            purchaseOrder.setDeliveryDate(LocalDate.now().plusDays(2L));
        } else {
            purchaseOrder.setDeliveryDate(LocalDate.now().plusDays(1L));
        }

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
    public String deleteCommandLine(final HttpSession session, final Long id) {
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
    public String modifCommandLine(final HttpSession session, final Long productId, final Float desiredQuantity) {
        PurchaseOrder purchaseOrder = (PurchaseOrder) session.getAttribute("purchaseOrder");
        int index = 0;
        for (CommandLine line : purchaseOrder.getLines()) {
            if (line.getProduct().getId().equals(productId)) {
                purchaseOrder.getLines().get(index).setDesiredQuantity(desiredQuantity);
            }
            index++;
        }
        session.setAttribute("purchaseOrder", purchaseOrder);
        return "redirect:purchaseorder";
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
