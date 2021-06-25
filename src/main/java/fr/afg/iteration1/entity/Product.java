package fr.afg.iteration1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter @NoArgsConstructor

@Entity
@Table
public class Product implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String ref;
    private String brand;
    private String imageUrl;
    private String origin;
    private String quantityUnity;
    @Column(length=500)
    private String description;
    private Float moq;
    private Boolean productIsActive;
    private Float lowPrice;
    private Float highPrice;
    
    
   @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_ingredient",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name ="ingredient_id")
    )
    private Set<Ingredient> ingredients=new HashSet<Ingredient>();
    
    @ManyToOne()
    @JoinColumn(referencedColumnName = "id")
    private ProductType productType;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Description> descriptions;
    
    @ManyToOne()
    @JoinColumn(referencedColumnName = "id")
    private Company supplier;

    public Product(String name, String ref, String brand, String imageUrl, String origin, String quantityUnity, String description, ProductType productType) {
        this.name = name;
        this.ref = ref;
        this.brand = brand;
        this.imageUrl = imageUrl;
        this.origin = origin;
        this.quantityUnity = quantityUnity;
        this.description = description;
        this.productType = productType;
    }

    public Product(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", ref=" + ref + ", brand=" + brand + ", imageUrl=" + imageUrl
				+ ", origin=" + origin + ", quantityUnity=" + quantityUnity + ", description=" + description + ", moq="
				+ moq + ", descriptions=" + descriptions + "]";
	}
    
}
