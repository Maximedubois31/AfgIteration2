package fr.afg.iteration1.service;

import java.util.LinkedList;
import java.util.List;
import fr.afg.iteration1.entity.Price;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Search {
    
    
    private String searchText;

    public Search(String searchText) {
        this.searchText = searchText;
    }

    public List<Price> searchForName(List<Price> pricesList) {
        
        List<Price> searchResult = new LinkedList<Price>();
			for (Price price : pricesList) {
				if (price.getProduct().getName().toLowerCase().contains(searchText.toLowerCase())) {
					searchResult.add(price);
				}
			}
        return searchResult;
    }
}