package fr.afg.iteration1.ui.response;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Tva response.
 */
@Getter @Setter @NoArgsConstructor @ToString
@Component
public class TvaResponse {
    
    private Long id;
    private String vatCode;
    private Double valueVat;
    private String label;
    private String category;


    /**
     * Instantiates a new Tva response.
     *
     * @param vatCode  the vat code
     * @param valueVat the value vat
     * @param label    the label
     * @param category the category
     */
    public TvaResponse(String vatCode, Double valueVat, String label, String category) {
        this.vatCode = vatCode;
        this.valueVat = valueVat;
        this.label = label;
        this.category = category;
    }

    /**
     * Instantiates a new Tva response.
     *
     * @param id       the id
     * @param vatCode  the vat code
     * @param valueVat the value vat
     * @param label    the label
     * @param category the category
     */
    public TvaResponse(Long id, String vatCode, Double valueVat, String label, String category) {
        this.vatCode = vatCode;
        this.valueVat = valueVat;
        this.label = label;
        this.category = category;
    }


    
}