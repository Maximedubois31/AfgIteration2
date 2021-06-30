package fr.afg.iteration1.service;

import fr.afg.iteration1.dao.UserDao;
import fr.afg.iteration1.entity.MyUsersDetails;
import fr.afg.iteration1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userDao.findByEmail(email);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found" + email + "or wrong password"));

        return user.map(MyUsersDetails::new).get();
    }
}
