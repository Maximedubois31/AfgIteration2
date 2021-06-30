package fr.afg.iteration1.delegate;

import java.util.List;

import fr.afg.iteration1.ui.response.CalculeTvaResponse;
import fr.afg.iteration1.ui.response.TvaResponse;

public interface TvaDelegate {
    
    CalculeTvaResponse getCalculeTva(Double price, Long id);
    
    List<TvaResponse> getAllTva();

    TvaResponse newTva(TvaResponse tva);

    void deleteTva(Long idTva);

    TvaResponse updateTva(TvaResponse tva);
    
}
