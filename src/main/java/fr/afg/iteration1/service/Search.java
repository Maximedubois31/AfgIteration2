package fr.afg.iteration1.service;

import fr.afg.iteration1.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

/**
 * The type Search.
 */
@Getter
@Setter
@NoArgsConstructor
public class Search {

    private String searchText;

    /**
     * Instantiates a new Search.
     *
     * @param searchText the search text
     */
    public Search(String searchText) {
        this.searchText = searchText;
    }

    /**
     * Search for name list.
     *
     * @param productsList the products list
     * @return the list
     */
    public List<Product> searchForName(List<Product> productsList) {

        List<Product> searchResult = new LinkedList<>();
        for (Product product : productsList) {
            if (product.getName().toLowerCase().contains(searchText.toLowerCase())) {
                searchResult.add(product);
            }
        }
        return searchResult;
    }

}