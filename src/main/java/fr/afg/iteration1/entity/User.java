package fr.afg.iteration1.entity;

import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type User.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean active;
    private String roles;
    @ManyToOne()
    @JoinColumn(referencedColumnName = "id")
    private Company company;

    /**
     * The Creator purchases.
     */
    @OneToMany(mappedBy = "creator")
    Set<PurchaseOrder> creatorPurchases;
    /**
     * The Customer purchases.
     */
    @OneToMany(mappedBy = "customer")
    Set<PurchaseOrder> customerPurchases;
    /**
     * The Preparator purchases.
     */
    @OneToMany(mappedBy = "preparator")
    Set<PurchaseOrder> preparatorPurchases;


}
