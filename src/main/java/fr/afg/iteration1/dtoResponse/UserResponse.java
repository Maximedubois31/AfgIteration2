package fr.afg.iteration1.dtoResponse;

import fr.afg.iteration1.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean active;
    private String roles;
    private Long companyId;

    public UserResponse(User user) {
    }

}
