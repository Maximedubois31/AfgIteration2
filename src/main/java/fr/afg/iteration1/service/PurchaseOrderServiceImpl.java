package fr.afg.iteration1.service;

import fr.afg.iteration1.dao.CommandLineDao;
import fr.afg.iteration1.dao.PurchaseOrderDao;
import fr.afg.iteration1.entity.CommandLine;
import fr.afg.iteration1.entity.PurchaseOrder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Purchase order service.
 */
@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderDao purchaseOrderDao;
    @Autowired
    private CommandLineDao commandLineDao;

    @Override
    public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder) {
        for (CommandLine line : purchaseOrder.getLines()) {
            commandLineDao.save(line);
        }
        return purchaseOrderDao.save(purchaseOrder);
    }

    @Override
    public PurchaseOrder deleteCommandLine(PurchaseOrder purchaseOrder, CommandLine commandLine) {
        purchaseOrder.getLines().remove(commandLine);
        return purchaseOrder;
    }

    @Override
    public PurchaseOrder addCommandLine(PurchaseOrder purchaseOrder, CommandLine commandLine) {
        purchaseOrder.getLines().add(commandLine);
        commandLine.setPurchaseOrder(purchaseOrder);
        return purchaseOrder;
    }

    @Override
    public void updatePurchaseOrder(final Long lineId, final Float desiredQuantity) {
        purchaseOrderDao.update(lineId, desiredQuantity);
    }

    @Override
    public List<PurchaseOrder> getAllOrderes() {
        return purchaseOrderDao.findAll();
    }

    @Override
    public List<PurchaseOrder> getAllMyOrderesInProgress(Long id) {
        return purchaseOrderDao.findByPurchaseOrderInProgress(id);
    }

    @Override
    public List<PurchaseOrder> getAllMyOrderesFinished(Long idUser) {
        return purchaseOrderDao.findByPurchaseOrderFinished(idUser);
    }

    @Override
    public PurchaseOrder getPoById(Long id) {
        return purchaseOrderDao.getById(id);
    }


}
