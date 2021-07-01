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

/**
 * The type Address.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Address {

    /**
     * The id of this adress.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The number of this adress.
     */
    private String number;
    /**
     * The street of this adress.
     */
    private String street;
    /**
     * The city of this adress.
     */
    private String city;
    /**
     * The postCode of this adress.
     */
    private String zip;
    /**
     * The country of this adress.
     */
    private String country;
    /**
     * The adresse type of this adress.
     */
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private AddressType address_type;

    /**
     * The Invoice addresse companies.
     */
    @OneToMany(mappedBy = "invoiceAddress")
    Set<Company> invoiceAddresseCompanies;
    /**
     * The Delivery addresse companies.
     */
    @OneToMany(mappedBy = "deliveryAddress")
    Set<Company> deliveryAddresseCompanies;

}
