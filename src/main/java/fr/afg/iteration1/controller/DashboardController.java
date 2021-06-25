package fr.afg.iteration1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.afg.iteration1.service.ProductService;
import fr.afg.iteration1.service.ProductTypeService;

@Controller
public class DashboardController {
	
	
	@Autowired
	ProductService productService;

	@Autowired
	ProductTypeService productTypeService;
	
	@GetMapping("dashboard")
	public String dashboard(Model model) {
		return "dashboard";
		
	}

	@GetMapping("/cruduser")
	public String crudUser(Model model) {
		return null;
	}
	
	@GetMapping("/crudProduct")
	public String CrudProduct(Model model) {
		return null;
		
	}

}
