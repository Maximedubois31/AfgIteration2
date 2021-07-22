package fr.afg.iteration1.dto;

import fr.afg.iteration1.entity.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class CompanyResponse {

    private Long id;
    private String companyName;
    private String invoiceAddress;
    private String deliveryAddress;
    private String siret;
    private String email;
    private String phone;
    private String vatNumber;
    private String nafApeCode;

    public CompanyResponse(Company company) {
    }
}
