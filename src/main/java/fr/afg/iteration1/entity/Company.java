package fr.afg.iteration1.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Address invoiceAddress;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Address deliveryAddress;
    private String siret;
    private String email;
    private String phone;
    private String vatNumber;
    private String nafApeCode;
    @OneToMany(mappedBy = "company")
    private Set<User> users;


}
