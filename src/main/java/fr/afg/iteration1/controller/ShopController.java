package fr.afg.iteration1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import fr.afg.iteration1.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.afg.iteration1.service.ProductService;
import fr.afg.iteration1.service.ProductTypeService;
import fr.afg.iteration1.service.PurchaseOrderService;
import fr.afg.iteration1.service.Search;
import fr.afg.iteration1.service.UserService;

@Controller
public class ShopController {

	@Autowired
	ProductService productService;

	@Autowired
	ProductTypeService productTypeService;
	
	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	@Autowired
	UserService userService;

	@GetMapping("/shop")
	public String listProducts(Model model) {

		model.addAttribute("newSearch", new Search());
		model.addAttribute("types", productTypeService.getAllProductType());
		model.addAttribute("filtre",new Filtre());
		model.addAttribute("commandLine", new CommandLine());
		model.addAttribute("products", productService.findByProductIsActive(true));

		return "shop";
	}

	@PostMapping("/shop")
	public String postListProducts(Model model, @ModelAttribute("newSearch") Search search, @ModelAttribute ("filtre")Filtre filtre) {

		List<Product> products = productService.findByProductIsActive(true);
		List<Product> filterProducts = new ArrayList<>();

		if (search.getSearchText() != null) {
			products = search.searchForName(products);
		}

		if (filtre.getFiltres() != null) {
			for (ProductType type : filtre.getFiltres()) {

				filterProducts.addAll(products
						.stream()
						.filter(c -> c.getProductType() == type)
						.collect(Collectors.toList())
				);
			}
		} else {
			filterProducts = products;
		}

		model.addAttribute("types", productTypeService.getAllProductType());
		model.addAttribute("products", filterProducts);
		model.addAttribute("filtre",filtre);
		model.addAttribute("commandLine", new CommandLine());

		return "shop";
	}
	
	@PostMapping("/addToPurchaseOrder")
	public String addToPurchaseOrder(Model model, @ModelAttribute("commandLine") CommandLine commandLine, HttpSession session) {

		PurchaseOrder purchaseOrder = (PurchaseOrder) session.getAttribute("purchaseOrder");
		for (CommandLine line: purchaseOrder.getLines()) {
			if(line.getProduct().getId().equals(commandLine.getProduct().getId())) {
				line.setDesiredQuantity(commandLine.getDesiredQuantity()+line.getDesiredQuantity());
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
