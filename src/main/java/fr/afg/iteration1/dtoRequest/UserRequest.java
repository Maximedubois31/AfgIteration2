package fr.afg.iteration1.dtoRequest;

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
    private Long companyId;

    public UserRequest(Long id, String firstName, String lastName, String email, String password, boolean active, String roles, Long companyId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.companyId = companyId;
    }
}
