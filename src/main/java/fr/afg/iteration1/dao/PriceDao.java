package fr.afg.iteration1.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.afg.iteration1.entity.Price;
import fr.afg.iteration1.entity.Product;

public interface PriceDao extends JpaRepository<Price, Long> {

	
//	@Query("SELECT p FROM Price p WHERE p.product_id = ?1 AND p.end_date=NULL")
//    Optional<Price> findActivePrice(long product_id);

	//rajouter ou p.date>= now
	@Query("SELECT p FROM Price p WHERE p.endDate=NULL")
	List<Price> findAllActivePrices();
	@Query("SELECT p FROM Price p WHERE p.product.id=?1 and p.endDate=NULL")
	List<Price> findActivePrice(Long id);
	@Query("SELECT p FROM Price p WHERE p.product.id =?1")
	List<Price> deleteByProductId(Long productId);

}
