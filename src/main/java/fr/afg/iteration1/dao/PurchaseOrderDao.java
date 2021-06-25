package fr.afg.iteration1.dao;

import fr.afg.iteration1.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderDao extends JpaRepository<PurchaseOrder, Long> {
}
