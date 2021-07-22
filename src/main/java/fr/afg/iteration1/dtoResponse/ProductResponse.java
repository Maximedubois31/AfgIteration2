package fr.afg.iteration1.dtoResponse;

import org.springframework.stereotype.Component;
import fr.afg.iteration1.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class ProductResponse {
    
    private Long id;
    /**
     * The name of my product
     */
    private String name;
    /**
     * The reference of my product
     */
    private String ref;
    /**
     * The brand of my product
     */
    private String brand;
    /**
     * The image associated to my product
     */
    private String imageUrl;
    /**
     * The origin of my product
     */
    private String origin;
    /**
     * The quantity unity(kg, piece...) of my product
     */
    private String quantityUnity;
    private String description;
    private Float moq;
    private Boolean productIsActive;
    private Float lowPrice;
    private Float highPrice;
    //private Set<Ingredient> ingredients = new HashSet<Ingredient>();
    //private String productType;
    //private List<Description> descriptions;
    //private Company supplier;
    private String company;
    private String productType;


    public ProductResponse(Product entity) {
        this.company = entity.getSupplier().getCompanyName();
        this.productType = entity.getProductType().getProductType();
    }

}
