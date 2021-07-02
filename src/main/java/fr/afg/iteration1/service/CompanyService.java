package fr.afg.iteration1.service;

import java.util.List;

import fr.afg.iteration1.entity.Address;
import fr.afg.iteration1.entity.AddressType;
import fr.afg.iteration1.entity.Company;

/**
 * The interface Company service.
 */
public interface CompanyService {

    /**
     * Save company.
     *
     * @param company the company
     * @return the company
     */
    Company saveCompany(Company company);

    /**
     * Find a company.
     *
     * @param id the company id
     * @return the company
     */
    Company findByIdCompany(Long id);

    /**
     * Gets all company.
     *
     * @return the all company
     */
    List<Company> getAllCompany();

    /**
     * Save address type address type.
     *
     * @param addressType the address type
     * @return the address type
     */
    AddressType saveAddressType(AddressType addressType);

    /**
     * Save address address.
     *
     * @param address the address
     * @return the address
     */
    Address saveAddress(Address address);

}