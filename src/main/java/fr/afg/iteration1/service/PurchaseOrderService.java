package fr.afg.iteration1.service;

import java.util.List;

import fr.afg.iteration1.entity.CommandLine;
import fr.afg.iteration1.entity.PurchaseOrder;

/**
 * The interface Purchase order service.
 */
public interface PurchaseOrderService {

    /**
     * Save purchase order purchase order.
     *
     * @param purchaseOrder the purchase order
     * @return the purchase order
     */
    PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder);

    /**
     * Delete command line purchase order.
     *
     * @param purchaseOrder the purchase order
     * @param commandLine   the command line
     * @return the purchase order
     */
    PurchaseOrder deleteCommandLine(PurchaseOrder purchaseOrder, CommandLine commandLine);

    /**
     * Add command line purchase order.
     *
     * @param purchaseOrder the purchase order
     * @param commandLine   the command line
     * @return the purchase order
     */
    PurchaseOrder addCommandLine(PurchaseOrder purchaseOrder, CommandLine commandLine);

    /**
     * Gets all orderes.
     *
     * @return the all orderes
     */
    List<PurchaseOrder> getAllOrderes();

    /**
     * Gets po by id.
     *
     * @param id the id
     * @return the po by id
     */
    PurchaseOrder getPoById(Long id);


}
