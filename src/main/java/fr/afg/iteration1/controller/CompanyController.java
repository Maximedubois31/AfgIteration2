package fr.afg.iteration1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.afg.iteration1.entity.Company;
import fr.afg.iteration1.entity.User;
import fr.afg.iteration1.service.CompanyService;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;


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
        

//        Address deliveryAddress = company.getDeliveryAddress();
//        Address invoiceAddress = company.getInvoiceAddress();

        companyService.saveCompany(company);
//        addressService.saveAddress(deliveryAddress);
//        addressService.saveAddress(invoiceAddress);

        return "redirect:/user";
    }
}
