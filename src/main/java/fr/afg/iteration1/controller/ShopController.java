package fr.afg.iteration1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.afg.iteration1.entity.CommandLine;
import fr.afg.iteration1.entity.Filtre;
import fr.afg.iteration1.entity.Price;
import fr.afg.iteration1.entity.ProductType;
import fr.afg.iteration1.entity.PurchaseOrder;
import fr.afg.iteration1.service.PriceService;
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
	PriceService priceService;

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
		model.addAttribute("prices", priceService.getAllActivePrices());
		model.addAttribute("filtre",new Filtre());
		model.addAttribute("commandLine", new CommandLine());


		return "shop";
	}


	@PostMapping("/shop")
	public String postListProducts(Model model, @ModelAttribute("newSearch") Search search, @ModelAttribute ("filtre")Filtre filtre) {

		List<Price> prices = priceService.getAllActivePrices();
		List<Price> filterPrices = new ArrayList<>(); 
		
		if (search.getSearchText() != null) {
			prices = search.searchForName(prices);
		}

		if (filtre.getFiltres() != null) {
			for (ProductType type : filtre.getFiltres()) {
				
				filterPrices.addAll(prices
				.stream()
				.filter(c -> c.getProduct().getProductType() == type)
				.collect(Collectors.toList())
				);
			}
		} else {
			filterPrices = prices;
		}
		
		

		model.addAttribute("types", productTypeService.getAllProductType());
		model.addAttribute("prices", filterPrices);
		model.addAttribute("filtre",filtre);
	

		return "shop";
	}
	
	@PostMapping("/addToPurchaseOrder")
	public String addToPurchaseOrder(Model model, @ModelAttribute("commandLine") CommandLine commandLine, HttpSession session) {

		PurchaseOrder purchaseOrder = (PurchaseOrder) session.getAttribute("purchaseOrder");
		for (CommandLine line: purchaseOrder.getLines()) {
			if(line.getProduct().getId().equals(commandLine.getProduct().getId())) {
				System.out.println("pass dans if");
				line.setDesiredQuantity(commandLine.getDesiredQuantity()+line.getDesiredQuantity());
				session.setAttribute("purchaseOrder", purchaseOrder);
				return "redirect:shop";
			}
		}
		purchaseOrderService.addCommandLine(purchaseOrder, commandLine);
		session.setAttribute("purchaseOrder", purchaseOrder);

		return "redirect:shop";
	}
	
	
}
