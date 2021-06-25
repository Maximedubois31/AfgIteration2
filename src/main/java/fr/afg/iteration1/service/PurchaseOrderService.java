package fr.afg.iteration1.service;

import java.util.List;

import fr.afg.iteration1.entity.CommandLine;
import fr.afg.iteration1.entity.PurchaseOrder;

public interface PurchaseOrderService {

    PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder);
    PurchaseOrder deleteCommandLine(PurchaseOrder purchaseOrder, CommandLine commandLine);
    PurchaseOrder addCommandLine(PurchaseOrder purchaseOrder, CommandLine commandLine);
    List<PurchaseOrder> getAllOrderes();
    PurchaseOrder getPoById(Long id);


}
