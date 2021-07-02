package fr.afg.iteration1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.afg.iteration1.entity.Address;
import fr.afg.iteration1.entity.Company;
import fr.afg.iteration1.entity.User;
import fr.afg.iteration1.service.AddressService;
import fr.afg.iteration1.service.CompanyService;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AddressService addressService;
    /**
     * Post product edit string.
     *
     * @param model   the model
     * @param user    the user
     * @param company the company
     * @return the string
     */
    @PostMapping("/company/edit")
    public String postCompanyEdit(final Model model, @ModelAttribute("user") final User user,
            @ModelAttribute("company") final Company company) {
        
        System.out.println("############################################################################");
        System.out.println(company.toString());
        System.out.println(company.getInvoiceAddress().getNumber());

        Address deliveryAddress = company.getDeliveryAddress();
        Address invoiceAddress = company.getInvoiceAddress();

        companyService.saveCompany(company);
        addressService.saveAddress(deliveryAddress);
        addressService.saveAddress(invoiceAddress);

        return "redirect:/user";
    }
}
