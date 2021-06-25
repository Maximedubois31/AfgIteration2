package fr.afg.iteration1.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afg.iteration1.dao.PriceDao;
import fr.afg.iteration1.entity.Price;
import fr.afg.iteration1.entity.Product;

@Service
@Transactional
public class PriceServiceImpl implements PriceService{
	
	@Autowired
	private PriceDao priceDao; 

	

	@Override
	public Float getRsp(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float getPriceForDate(Product product, LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Price> getAllActivePrices() {
		List<Price>prices = priceDao.findAllActivePrices();
				
		return prices;
	}

	@Override
	public Price getPrice(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Price> deleteByProductId(Long productId) {
		return priceDao.deleteByProductId(productId);
	}

	

}
