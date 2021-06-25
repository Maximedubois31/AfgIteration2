package fr.afg.iteration1.service;

import java.util.List;

import fr.afg.iteration1.entity.User;

public interface UserService {

	User saveUser(User user);
	List<User> getAllUsers();
	User getUserById(Long id);
	User getUserByEmail(String email);
}
