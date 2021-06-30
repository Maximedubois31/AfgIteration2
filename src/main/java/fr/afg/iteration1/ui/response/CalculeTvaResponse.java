package fr.afg.iteration1.ui.response;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CalculeTvaResponse {
    
    private Double valueVat;
    private String label;
    private String category;
    private Double amountTva;
    private Double initialPrice;
    private Double finalPrice;


    public CalculeTvaResponse(Double valueVat, String label, String category, Double amountTva, Double initialPrice,
            Double finalPrice) {
        this.valueVat = valueVat;
        this.label = label;
        this.category = category;
        this.amountTva = amountTva;
        this.initialPrice = initialPrice;
        this.finalPrice = finalPrice;
    }
}
