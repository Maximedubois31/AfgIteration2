package fr.afg.iteration1.service;

import fr.afg.iteration1.entity.PurchaseOrder;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface ExcelService {

    void creerExcel(HttpSession session, PurchaseOrder order) throws IOException, InvalidFormatException;
}
