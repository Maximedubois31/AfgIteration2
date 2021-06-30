package fr.afg.iteration1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afg.iteration1.dao.AddressDao;
import fr.afg.iteration1.dao.AddressTypeDao;
import fr.afg.iteration1.dao.CompanyDao;
import fr.afg.iteration1.entity.Address;
import fr.afg.iteration1.entity.AddressType;
import fr.afg.iteration1.entity.Company;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private AddressTypeDao addressTypeDao;

    @Autowired
    private AddressDao addressDao;

    @Override
    public List<Company> getAllCompany() {
        return companyDao.findAll();
    }

    @Override
    public Company saveCompany(Company company) {
        return companyDao.save(company);
    }

    @Override
    public AddressType saveAddressType(AddressType addressType) {
        return addressTypeDao.save(addressType);
    }

    @Override
    public Address saveAddress(Address address) {
        return addressDao.save(address);
    }


}
