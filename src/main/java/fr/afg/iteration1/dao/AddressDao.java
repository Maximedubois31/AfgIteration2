package fr.afg.iteration1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afg.iteration1.entity.Address;

/**
 * The interface Address dao.
 */
public interface AddressDao extends JpaRepository<Address, Long> {
    
}
