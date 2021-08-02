package fr.afg.iteration1.service;

import java.util.List;

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


    void deleteCompany(Company company);

}