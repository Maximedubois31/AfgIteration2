package fr.afg.iteration1.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afg.iteration1.dao.AddressDao;
import fr.afg.iteration1.entity.Address;

/**
 * The type Address service.
 */
@Service
@Transactional
public class AddressServiceImpl implements AddressService{
    
    @Autowired
    private AddressDao addressDao;

    
    @Override
    public Address saveAddress(Address address) {
        return addressDao.save(address);
    }
}
