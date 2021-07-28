package fr.afg.iteration1.restController;

import fr.afg.iteration1.dtoRequest.UserRequest;
import fr.afg.iteration1.dtoResponse.UserResponse;
import fr.afg.iteration1.entity.User;
import fr.afg.iteration1.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value="/admin", headers="Accept=application/json")
public class UserRestController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {

        try {
            List<User> users = userService.getAllUsers();
            List<UserResponse> response = new LinkedList<>();
            UserResponse userResponse = null;

            for(User user : users) {
                userResponse = new UserResponse(user);
                BeanUtils.copyProperties(user, userResponse);
                userResponse.setCompany(user.getCompany().getCompanyName());
                response.add(userResponse);
            }
            return new ResponseEntity<List<UserResponse>>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("La recherche n'a pas aboutie. ", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/details/{id}")
    public ResponseEntity<?> getUserDetails(@PathVariable("id") Long id) {

        try {
            User user = userService.getUserById(id);
            UserResponse userResponse = new UserResponse(user);
            userResponse.setCompany(user.getCompany().getCompanyName());
            BeanUtils.copyProperties(user, userResponse);

            return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("La recherche n'a pas abouti. Problèmes possibles : identifiant", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/new")
    public ResponseEntity<?> newUser(@RequestBody User user) {

        try {
            User newUser = userService.saveUser(user);
            UserRequest userRequest = new UserRequest(newUser);
            BeanUtils.copyProperties(user, userRequest);

            return new ResponseEntity<UserRequest>(userRequest, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Impossible d'enregistrer les données", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/user/edit")
    public ResponseEntity<?> userEdit(@RequestBody User user) {

        try {
            User userUpdate = userService.saveUser(user);
            UserRequest userRequest = new UserRequest(userUpdate);
            BeanUtils.copyProperties(user, userRequest);

            return new ResponseEntity<UserRequest>(userRequest, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Impossible d'enregistrer les données", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("user/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {

        try {
            User userDelete = userService.findById(id);
            userDelete.setActive(false);
            userService.saveUser(userDelete);

            return new ResponseEntity<String>("Utilisateur désactivé", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Impossible de désactiver l'utilisateur", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
