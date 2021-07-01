package fr.afg.iteration1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afg.iteration1.entity.AddressType;

/**
 * The interface Address type dao.
 */
public interface AddressTypeDao extends JpaRepository<AddressType, Long> {
    
}
