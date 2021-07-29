package fr.afg.iteration1.restController;

import com.google.gson.Gson;
import fr.afg.iteration1.dtoRequest.UserRequest;
import fr.afg.iteration1.dtoResponse.ProductResponse;
import fr.afg.iteration1.dtoResponse.UserResponse;
import fr.afg.iteration1.entity.User;
import fr.afg.iteration1.service.CompanyService;
import fr.afg.iteration1.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/admin", headers = "Accept=application/json")
public class UserRestController {

    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;

    private static final Gson gson = new Gson();
    private ResponseEntity<UserRequest> userRequestResponseEntity;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {

        try {
            List<User> users = userService.getAllUsers();
            List<UserResponse> response = new LinkedList<>();
            UserResponse response1 = null;

            for (User user : users) {
                response1 = new UserResponse(user);
                BeanUtils.copyProperties(user, response1);
                response1.setCompany(user.getCompany().getCompanyName());
                response.add(response1);
            }
            return new ResponseEntity<List<UserResponse>>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(gson.toJson("La recherche n'a pas aboutie. "), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/details/{id}")
    public ResponseEntity<?> getUserDetails(@PathVariable("id") Long id) {

        try {
            User user = userService.getUserById(id);
            UserResponse response = new UserResponse(user);
            response.setCompany(user.getCompany().getCompanyName());
            BeanUtils.copyProperties(user, response);

            return new ResponseEntity<UserResponse>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(gson.toJson("La recherche n'a pas abouti. Problèmes possibles : identifiant"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/user/new")
    public ResponseEntity<?> newUser(@RequestBody User user) {

        /*try {
            User newUser = userService.saveUser(user);
            //UserRequest userRequest = new UserRequest(newUser);
            BeanUtils.copyProperties(user, userRequest);

            return new ResponseEntity<UserRequest>(userRequest, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(gson.toJson("Impossible d'enregistrer les données"), HttpStatus.BAD_REQUEST);
        }*/
        return new ResponseEntity<String>(gson.toJson("Impossible d'enregistrer les données"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/user/edit")
    public ResponseEntity<?> userEdit(@RequestBody UserRequest user) {

        try {
            User userUpdate = new User();

            userUpdate.setId(user.getId());
            userUpdate.setFirstName(user.getFirstName());
            userUpdate.setLastName(user.getLastName());
            userUpdate.setEmail(user.getEmail());
            //Check if the password is encode, if the password is encode save it, else encode it
            char tab[] = user.getPassword().toCharArray();
            if(tab[0] == '$' && tab[1] == '2' && tab[2] == 'a' && tab[3] == '$' && tab[4] == '1' && tab[5] == '2' && tab[6] == '$'){
                userUpdate.setPassword(user.getPassword());
            } else {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
                userUpdate.setPassword(encoder.encode(user.getPassword()));
            }
            userUpdate.setActive(user.isActive());
            userUpdate.setRoles(user.getRoles());
            //userUpdate.setCompany(companyService.findByIdCompany(1L));
            System.out.println(user.getCompanyId());
            userUpdate.setCompany(companyService.findByIdCompany(user.getCompanyId()));

            //Save
            User userAdd =  userService.saveUser(userUpdate);

            //SET response
            UserResponse response = new UserResponse(userUpdate);
            BeanUtils.copyProperties(userAdd, response);
            response.setCompany(companyService.findByIdCompany(1L).getCompanyName());

            return new ResponseEntity<UserResponse>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(gson.toJson("Impossible d'enregistrer les données"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("user/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {

        try {
            User userDelete = userService.findById(id);
            userDelete.setActive(false);
            userService.saveUser(userDelete);

            return new ResponseEntity<String>(gson.toJson("Utilisateur désactivé"), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(gson.toJson("Impossible de désactiver l'utilisateur"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
