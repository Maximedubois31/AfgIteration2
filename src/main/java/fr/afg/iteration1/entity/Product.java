package fr.afg.iteration1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table
public class Product {

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
    @Column(length = 500)
    private String description;
    private Float moq;
    private Boolean productIsActive;
    private Float lowPrice;
    private Float highPrice;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_ingredient",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Set<Ingredient> ingredients = new HashSet<Ingredient>();

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id")
    private ProductType productType;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Description> descriptions;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id")
    private Company supplier;

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", ref=" + ref
                + ", brand=" + brand + ", imageUrl=" + imageUrl
                + ", origin=" + origin + ", quantityUnity="
                + quantityUnity + ", description=" + description + ", moq="
                + moq + ", descriptions=" + descriptions + "]";
    }

}
