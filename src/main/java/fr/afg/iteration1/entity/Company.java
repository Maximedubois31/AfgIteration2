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
import lombok.ToString;

/**
 * The type Company.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
public class Company {

    /**
     * The id of this company.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The name of this company.
     */
    private String companyName;
    /**
     * The invoide adress of this company.
     */
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Address invoiceAddress;
    /**
     * The delivery adress of this company.
     */
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Address deliveryAddress;
    /**
     * The siret of this company.
     */
    private String siret;
    /**
     * The email of this company.
     */
    private String email;
    /**
     * The phone number of this company.
     */
    private String phone;
    private String vatNumber;
    private String nafApeCode;
    @OneToMany(mappedBy = "company")
    private Set<User> users;


}
