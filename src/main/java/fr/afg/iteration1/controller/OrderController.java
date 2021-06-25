package fr.afg.iteration1.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

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
import fr.afg.iteration1.service.PriceService;
import fr.afg.iteration1.service.ProductService;
import fr.afg.iteration1.service.ProductTypeService;
import fr.afg.iteration1.service.PurchaseOrderService;
import fr.afg.iteration1.service.Search;
import fr.afg.iteration1.service.UserService;

@SessionAttributes(value = {"order"})
@Controller
public class OrderController {

	@Autowired
	ProductService productService;

	@Autowired
	PriceService priceService;

	@Autowired
	ProductTypeService productTypeService;

	@Autowired
	PurchaseOrderService purchaseOrderService;
	
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
	public String getSelectedOrder(Model model, HttpSession session) {
		PurchaseOrder order = (PurchaseOrder) session.getAttribute("order");
		model.addAttribute("order", order);
		return "orderselectedpreparator";
	}

	@PostMapping("updateQuantity")
	public String updateOrderedQuantity(HttpSession session, Long productId, Float orderedQuantity, Model model) {
		PurchaseOrder order = (PurchaseOrder) session.getAttribute("order");
		System.out.println(order.getCreator().getFirstName());
		System.out.println("--------------------------");
		System.out.println("quantité fournie " + orderedQuantity);
		System.out.println("--------------------------");
		System.out.println("productId " + productId);
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
		System.out.println("--------------------------");
		System.out.println("Order Id " + order.getId());

		session.setAttribute("order", order);
		model.addAttribute("order", order);
		return "redirect:to-orderselectedpreparator";
	}

	@PostMapping("validateSelectedOrder")
	public String validateSelectedOrder(Model model, HttpSession session) {
		PurchaseOrder order = (PurchaseOrder) session.getAttribute("order");
		User user = userService.getUserById((Long) session.getAttribute("idUser"));
		order.setPreparator(user);
		order.setPreparationDate(LocalDate.now());
		purchaseOrderService.savePurchaseOrder(order);
		session.removeAttribute("order");
		return "redirect:orderes";
	}
}
