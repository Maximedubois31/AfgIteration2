package fr.afg.iteration1.service;

import fr.afg.iteration1.entity.Address;

/**
 * The interface Company service.
 */
public interface AddressService {
    
    /**
     * Save company address.
     *
     * @param address the address
     * @return the address
     */
    Address saveAddress(Address address);
}
