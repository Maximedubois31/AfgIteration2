package fr.afg.iteration1.delegate;

import java.util.List;

import fr.afg.iteration1.ui.response.CalculeTvaResponse;
import fr.afg.iteration1.ui.response.TvaResponse;

/**
 * The interface Tva delegate.
 */
public interface TvaDelegate {

    /**
     * Gets calcule tva.
     *
     * @param price the price
     * @param id    the id
     * @return the calcule tva
     */
    CalculeTvaResponse getCalculeTva(Double price, Long id);

    /**
     * Gets all tva.
     *
     * @return the all tva
     */
    List<TvaResponse> getAllTva();

    /**
     * New tva tva response.
     *
     * @param tva the tva
     * @return the tva response
     */
    TvaResponse newTva(TvaResponse tva);

    /**
     * Delete tva.
     *
     * @param idTva the id tva
     */
    void deleteTva(Long idTva);

    /**
     * Update tva tva response.
     *
     * @param tva the tva
     * @return the tva response
     */
    TvaResponse updateTva(TvaResponse tva);
    
}
