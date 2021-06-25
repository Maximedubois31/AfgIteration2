package fr.afg.iteration1.service;

import java.util.List;

import fr.afg.iteration1.entity.Address;
import fr.afg.iteration1.entity.AddressType;
import fr.afg.iteration1.entity.Company;

public interface CompanyService {

    Company saveCompany(Company company);

    List<Company> getAllCompany();
    
    AddressType saveAddressType(AddressType addressType);
    
    Address saveAddress(Address address);

}