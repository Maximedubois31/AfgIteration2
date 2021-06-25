package fr.afg.iteration1.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor

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
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Company company;
	
	@OneToMany(mappedBy = "creator")
	Set<PurchaseOrder> creatorPurchases;
	@OneToMany(mappedBy = "customer")
	Set<PurchaseOrder> customerPurchases;
	@OneToMany(mappedBy = "preparator")
	Set<PurchaseOrder> preparatorPurchases;

	public User(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(Long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(String firstName, String email, String password, boolean active, String roles) {
		this.firstName = firstName;
		this.email = email;
		this.active = active;
		this.password = password;
		this.roles = roles;
	}

}
