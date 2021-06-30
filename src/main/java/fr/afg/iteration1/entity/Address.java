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
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String street;
    private String city;
    private String zip;
    private String country;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private AddressType address_type;

    @OneToMany(mappedBy = "invoiceAddress")
    Set<Company> invoiceAddresseCompanies;
    @OneToMany(mappedBy = "deliveryAddress")
    Set<Company> deliveryAddresseCompanies;

}
