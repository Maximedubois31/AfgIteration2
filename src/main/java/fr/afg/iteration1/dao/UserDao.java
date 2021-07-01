package fr.afg.iteration1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afg.iteration1.entity.User;

import java.util.Optional;

/**
 * The interface User dao.
 */
public interface UserDao extends JpaRepository<User, Long> {
    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     */
    Optional<User> findByEmail(String email);
    //Optional<User> findById(Long id);

}
