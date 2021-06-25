package fr.afg.iteration1.service;

import java.time.LocalDate;
import java.util.List;

import fr.afg.iteration1.entity.Price;
import fr.afg.iteration1.entity.Product;

public interface PriceService {
	
	Price getPrice (Product product);
	List<Price> getAllActivePrices();
	Float getRsp (Product product);
	Float getPriceForDate(Product product, LocalDate date);
	List<Price> deleteByProductId(Long productId);

}
