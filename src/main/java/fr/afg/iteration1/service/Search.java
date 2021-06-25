package fr.afg.iteration1.service;

import fr.afg.iteration1.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Search {

    private String searchText;

    public Search(String searchText) {
        this.searchText = searchText;
    }

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