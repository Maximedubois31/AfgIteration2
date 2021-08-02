package fr.afg.iteration1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afg.iteration1.dao.CompanyDao;
import fr.afg.iteration1.entity.Company;

/**
 * The type Company service.
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;



    @Override
    public List<Company> getAllCompany() {
        return companyDao.findAll();
    }

    @Override
    public Company saveCompany(Company company) {
        return companyDao.save(company);
    }

    @Override
    public void deleteCompany(Company company) {
        companyDao.delete(company);
    }

    @Override
    public Company findByIdCompany(Long id) {
        return companyDao.findById(id).get();
    }

    

}
