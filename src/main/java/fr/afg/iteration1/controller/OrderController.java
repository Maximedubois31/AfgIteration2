package fr.afg.iteration1.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import fr.afg.iteration1.service.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import fr.afg.iteration1.entity.CommandLine;
import fr.afg.iteration1.entity.Filtre;
import fr.afg.iteration1.entity.PurchaseOrder;
import fr.afg.iteration1.entity.User;

@SessionAttributes(value = {"order"})
@Controller
public class OrderController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductTypeService productTypeService;

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Autowired
    ExcelService excelService;

    @Autowired
    UserService userService;

    @GetMapping("/orderes")
    public String listOrderes(Model model) {

        model.addAttribute("newSearch", new Search());
        model.addAttribute("types", productTypeService.getAllProductType());
        model.addAttribute("orderes", purchaseOrderService.getAllOrderes());
        model.addAttribute("filtre", new Filtre());

        return "orderes";
    }

    @GetMapping("/to-orderpreparator")
    public String getOrder(Model model, @RequestParam("idPo") Long idPo) {
        PurchaseOrder order = purchaseOrderService.getPoById(idPo);
        model.addAttribute("order", order);
        return "orderpreparator";
    }

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

    @PostMapping("updateQuantity")
    public String updateOrderedQuantity(HttpSession session,
                                        Long productId,
                                        Float orderedQuantity,
                                        Model model) {
        PurchaseOrder order = (PurchaseOrder) session.getAttribute("order");
        CommandLine lineToDelete = new CommandLine();
        CommandLine lineToUpdate = new CommandLine();
        for (CommandLine line : order.getLines()) {
            if (line.getProduct().getId().equals(productId)) {
                lineToDelete = line;
                lineToUpdate = line;
                lineToUpdate.setOrderedQuantity(orderedQuantity);
            }
        }

        order.getLines().remove(lineToDelete);
        order.getLines().add(lineToUpdate);

        session.setAttribute("order", order);
        model.addAttribute("order", order);
        return "redirect:to-orderselectedpreparator";
    }

    @PostMapping("validateSelectedOrder")
    public String validateSelectedOrder(Model model,
                                        HttpSession session) throws IOException, InvalidFormatException {
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