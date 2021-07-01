package fr.afg.iteration1.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import fr.afg.iteration1.ui.response.CalculeTvaResponse;
import fr.afg.iteration1.ui.response.TvaResponse;

/**
 * The type Tva delegate.
 */
@Component
public class TvaDelegateImpl implements TvaDelegate{

    private RestTemplate restTemplate;

    /**
     * Instantiates a new Tva delegate.
     */
    public TvaDelegateImpl() {
        restTemplate = new RestTemplate();
    }

    @Override
    public List<TvaResponse> getAllTva() {
        
        String urlApi = "http://localhost:8094/vat-api-rest/tva/list";
        TvaResponse[] tvaList = restTemplate.getForObject(urlApi, TvaResponse[].class);

        return Arrays.asList(tvaList);
    }

    @Override
    public CalculeTvaResponse getCalculeTva(Double price, Long tvaId) {
        
        String urlApi = "http://localhost:8094/vat-api-rest/tva/" + price + "/" + tvaId;
        CalculeTvaResponse response = restTemplate.getForObject(urlApi, CalculeTvaResponse.class);

        return response;
    }

    @Override
    public TvaResponse newTva(TvaResponse tva) {
        
        String urlApi = "http://localhost:8094/vat-api-rest/tva/new";

        return restTemplate.postForObject(urlApi, tva, TvaResponse.class);
    }

    @Override
    public void deleteTva(Long idTva) {
        String urlApi = "http://localhost:8094/vat-api-rest/tva/delete/" + idTva;

        restTemplate.delete(urlApi);
        
    }

    @Override
    public TvaResponse updateTva(TvaResponse tva) {
        String urlApi = "http://localhost:8094/vat-api-rest/tva/update";
        
        restTemplate.put(urlApi, tva);
        return tva;
    }

    
    
    
}
