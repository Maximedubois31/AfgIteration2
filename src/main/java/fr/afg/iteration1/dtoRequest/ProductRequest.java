package fr.afg.iteration1.dtoRequest;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Component
@ToString
public class ProductRequest {
    
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
    private Long productTypeId;
    private String descriptions;
    private Long supplierId;


    
    public ProductRequest(Long id, String name, String ref, String brand, String imageUrl, String origin, String quantityUnity,
            String description, Float moq, Boolean productIsActive, Float lowPrice, Float highPrice, Long productTypeId,
            String descriptions, Long supplierId) {
        this.name = name;
        this.ref = ref;
        this.brand = brand;
        this.imageUrl = imageUrl;
        this.origin = origin;
        this.quantityUnity = quantityUnity;
        this.description = description;
        this.moq = moq;
        this.productIsActive = productIsActive;
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.productTypeId = productTypeId;
        this.descriptions = descriptions;
        this.supplierId = supplierId;
    }


    
    


    
}
