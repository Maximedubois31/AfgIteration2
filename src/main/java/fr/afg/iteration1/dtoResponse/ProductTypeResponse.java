package fr.afg.iteration1.dtoResponse;

import org.springframework.stereotype.Component;

import fr.afg.iteration1.entity.ProductType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class ProductTypeResponse {
    
    private Long id;
    private String productType;

    public ProductTypeResponse(ProductType productType){}
}
