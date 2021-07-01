package fr.afg.iteration1.dao;

import fr.afg.iteration1.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * The interface Purchase order dao.
 */
public interface PurchaseOrderDao extends JpaRepository<PurchaseOrder, Long> {
    /**
     * Find by delivery date list.
     *
     * @param localDate the local date
     * @return the list
     */
    List<PurchaseOrder> findByDeliveryDate(LocalDate localDate);
    @Query("SELECT p FROM PurchaseOrder p WHERE p.preparationDate IS NULL AND p.creator.id = ?1")
    List<PurchaseOrder> findByPurchaseOrderInProgress(Long id);
    @Query("SELECT p FROM PurchaseOrder p WHERE p.preparationDate IS NOT NULL AND p.creator.id = ?1")
    List<PurchaseOrder> findByPurchaseOrderFinished(Long id);
    @Transactional
    @Modifying
    @Query("UPDATE FROM CommandLine c set c.desiredQuantity = ?2 WHERE c.id = ?1")
    void update(Long id, Float desiredQuantity);

}
