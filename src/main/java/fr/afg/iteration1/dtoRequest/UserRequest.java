package fr.afg.iteration1.dtoRequest;

import fr.afg.iteration1.entity.Company;
import fr.afg.iteration1.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class UserRequest {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean active;
    private String roles;
    private Company company;

    public UserRequest(User user) {
    }

}
