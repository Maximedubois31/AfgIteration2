package fr.afg.iteration1.service;

import fr.afg.iteration1.entity.PurchaseOrder;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The interface Excel service.
 */
public interface ExcelService {

    /**
     * Creer excel.
     *
     * @param session the session
     * @param order   the order
     * @throws IOException the io exception
     */
    void creerExcel(HttpSession session, PurchaseOrder order) throws IOException;
}
