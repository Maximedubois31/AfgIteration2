package fr.afg.iteration1.dao;

import fr.afg.iteration1.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
