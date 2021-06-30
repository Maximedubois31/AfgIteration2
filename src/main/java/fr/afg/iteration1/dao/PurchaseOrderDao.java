package fr.afg.iteration1.dao;

import fr.afg.iteration1.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PurchaseOrderDao extends JpaRepository<PurchaseOrder, Long> {
    List<PurchaseOrder> findByDeliveryDate(LocalDate localDate);
}
