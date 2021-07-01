package fr.afg.iteration1.ui.response;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Calcule tva response.
 */
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


    /**
     * Instantiates a new Calcule tva response.
     *
     * @param valueVat     the value vat
     * @param label        the label
     * @param category     the category
     * @param amountTva    the amount tva
     * @param initialPrice the initial price
     * @param finalPrice   the final price
     */
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
