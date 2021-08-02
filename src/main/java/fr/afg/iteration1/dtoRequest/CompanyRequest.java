package fr.afg.iteration1.dtoRequest;

import org.springframework.stereotype.Component;

import fr.afg.iteration1.entity.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class CompanyRequest {

    private Long id;
    private String companyName;
    private String invoiceAddress;
    private String deliveryAddress;
    private String siret;
    private String email;
    private String phone;
    private String vatNumber;
    private String nafApeCode;

    public CompanyRequest(Company company) {
    }
}
