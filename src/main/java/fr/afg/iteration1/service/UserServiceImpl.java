package fr.afg.iteration1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afg.iteration1.dao.UserDao;
import fr.afg.iteration1.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }


    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }


    @Override
    public User getUserById(Long id) {
        return userDao.getById(id);
    }


    @Override
    public User getUserByEmail(String email) {
        return userDao.findByEmail(email).get();
    }

}
