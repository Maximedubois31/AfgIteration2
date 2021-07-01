package fr.afg.iteration1.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Address type.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "address_type")
public class AddressType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * The id of this AdressType
     */
    private Long id;
    /**
     * The name of this AdressType.
     */
    private String name;
    
    @OneToMany(mappedBy = "address_type")
    private Set<Address> adresses;

}
