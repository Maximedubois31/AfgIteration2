package fr.afg.iteration1.service;

import java.util.List;

import fr.afg.iteration1.entity.User;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Save user user.
     *
     * @param user the user
     * @return the user
     */
    User saveUser(User user);

    /**
     * Gets all users.
     *
     * @return the all users
     */
    List<User> getAllUsers();

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    User getUserById(Long id);

    /**
     * Gets user by email.
     *
     * @param email the email
     * @return the user by email
     */
    User getUserByEmail(String email);
}
