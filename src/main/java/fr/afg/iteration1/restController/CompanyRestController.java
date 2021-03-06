package fr.afg.iteration1.restController;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.afg.iteration1.dtoRequest.CompanyRequest;
import fr.afg.iteration1.dtoResponse.CompanyResponse;
import fr.afg.iteration1.entity.Company;
import fr.afg.iteration1.service.CompanyService;

@RestController
@CrossOrigin
@RequestMapping(value="/admin", headers="Accept=application/json")
public class CompanyRestController {

    @Autowired
    CompanyService companyService;


    @GetMapping("/company")
    public ResponseEntity<?> getAllCompany() {

        try {
            List<Company> companies = companyService.getAllCompany();
            List<CompanyResponse> response = new LinkedList<>();
            CompanyResponse companyResponse = null;

            for (Company company: companies) {
                companyResponse = new CompanyResponse(company);
                BeanUtils.copyProperties(company, companyResponse);
//                companyResponse.setDeliveryAddress(company.getDeliveryAddress().toString());
//                companyResponse.setInvoiceAddress(company.getInvoiceAddress().toString());
                response.add(companyResponse);
            }
            return new ResponseEntity<List<CompanyResponse>>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("La recherche n'a pas aboutie. ", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/company/details/{id}")
    public ResponseEntity<?> getCompanyDetails(@PathVariable("id") Long id) {

        try {
            Company company = companyService.findByIdCompany(id);
            CompanyResponse companyResponse = new CompanyResponse(company);
            BeanUtils.copyProperties(company, companyResponse);
//            companyResponse.setDeliveryAddress(company.getDeliveryAddress().toString());
//            companyResponse.setInvoiceAddress(company.getInvoiceAddress().toString());
            return new ResponseEntity<CompanyResponse>(companyResponse, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("La recherche n'a pas abouti. Probl??mes possibles??: identifiant", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/company/new")
    public ResponseEntity<?> newCompany(@RequestBody Company company) {

        try {
            Company newCompany = companyService.saveCompany(company);
            CompanyRequest companyRequest = new CompanyRequest(company);
            BeanUtils.copyProperties(company, companyRequest);

            return new ResponseEntity<CompanyRequest>(companyRequest, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Impossible d'enregistrer les donn??es", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/company/edit")
    public ResponseEntity<?> companyEdit(@RequestBody Company company) {

        try {
            Company companyUpdate = companyService.saveCompany(company);
            CompanyRequest companyRequest = new CompanyRequest(companyUpdate);
            BeanUtils.copyProperties(company, companyRequest);

            return new ResponseEntity<CompanyRequest>(companyRequest, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Impossible d'enregistrer les donn??es", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("company/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {

        try {
            Company companyDelete = companyService.findByIdCompany(id);
            companyService.deleteCompany(companyDelete);

            return new ResponseEntity<String>("Deleted", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Impossible de supprimer les donn??es", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
