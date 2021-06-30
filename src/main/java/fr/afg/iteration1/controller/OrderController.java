package fr.afg.iteration1.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import fr.afg.iteration1.service.ProductService;
import fr.afg.iteration1.service.ProductTypeService;
import fr.afg.iteration1.service.PurchaseOrderService;
import fr.afg.iteration1.service.Search;
import fr.afg.iteration1.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import fr.afg.iteration1.entity.CommandLine;
import fr.afg.iteration1.entity.Filtre;
import fr.afg.iteration1.entity.PurchaseOrder;
import fr.afg.iteration1.entity.User;

@SessionAttributes(value = {"order"})
@Controller
public class OrderController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductTypeService productTypeService;

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Autowired
    UserService userService;

    @GetMapping("/orderes")
    public String listOrderes(Model model) {

        model.addAttribute("newSearch", new Search());
        model.addAttribute("types", productTypeService.getAllProductType());
        model.addAttribute("orderes", purchaseOrderService.getAllOrderes());
        model.addAttribute("filtre", new Filtre());

        return "orderes";
    }

    @GetMapping("/to-orderpreparator")
    public String getOrder(Model model, @RequestParam("idPo") Long idPo) {
        PurchaseOrder order = purchaseOrderService.getPoById(idPo);
        model.addAttribute("order", order);
        return "orderpreparator";
    }

    @GetMapping("/to-orderselectedpreparator")
    public String getSelectedOrder(Model model,
                                   HttpSession session) {
        PurchaseOrder order = (PurchaseOrder) session.getAttribute("order");
        model.addAttribute("order", order);
        Float total = 0f;
        for (CommandLine line : order.getLines()) {
            if (line.getOrderedQuantity() != null) {
                total = total
                        + line.getActivePrice()
                        * line.getProduct().getMoq()
                        * line.getOrderedQuantity();
            }
        }
        model.addAttribute("total", total);
        return "orderselectedpreparator";
    }

    @PostMapping("updateQuantity")
    public String updateOrderedQuantity(HttpSession session,
                                        Long productId,
                                        Float orderedQuantity,
                                        Model model) {
        PurchaseOrder order = (PurchaseOrder) session.getAttribute("order");
        CommandLine lineToDelete = new CommandLine();
        CommandLine lineToUpdate = new CommandLine();
        for (CommandLine line : order.getLines()) {
            if (line.getProduct().getId().equals(productId)) {
                lineToDelete = line;
                lineToUpdate = line;
                lineToUpdate.setOrderedQuantity(orderedQuantity);
            }
        }

        order.getLines().remove(lineToDelete);
        order.getLines().add(lineToUpdate);

        session.setAttribute("order", order);
        model.addAttribute("order", order);
        return "redirect:to-orderselectedpreparator";
    }

    @PostMapping("validateSelectedOrder")
    public String validateSelectedOrder(Model model,
                                        HttpSession session) {
        PurchaseOrder order = (PurchaseOrder) session.getAttribute("order");
        User user = userService.getUserById((Long) session.getAttribute("idUser"));
        order.setPreparator(user);
        order.setPreparationDate(LocalDate.now());
        purchaseOrderService.savePurchaseOrder(order);
        session.removeAttribute("order");
        return "redirect:orderes";
    }

    @PostMapping("createExcel")
    public String createExcel(HttpSession session) throws IOException,
                                                            InvalidFormatException {

        PurchaseOrder order = (PurchaseOrder) session.getAttribute("order");

        //Remove space from company name
        String[] companyNameWithoutSpace = order.getCreator()
                                                .getCompany()
                                                .getCompanyName()
                                                .split(" ");

        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < companyNameWithoutSpace.length; ++i) {
            buf.append(companyNameWithoutSpace[i]);
        }
        String companyName = buf.toString();

        String excelFilePath = "C:\\Users\\duboi\\OneDrive\\Bureau\\" + companyName + ".xls";
        File file = new File(excelFilePath);
        //Créer un fichier excel si il n'y en a pas pour la company
        if (!file.exists()) {
            HSSFWorkbook wb = new HSSFWorkbook();
            OutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(excelFilePath);
                wb.write(outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    wb.close();
                    outputStream.close();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

            file = new File(excelFilePath);
        }
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        HSSFWorkbook workbook = null;
        try {
            inputStream = new FileInputStream(file);
            workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet;
            if (workbook.getSheetIndex("Commande n°" + order.getId()) == -1) {
                sheet = workbook.createSheet("Commande n°" + order.getId());
            } else {
                sheet = workbook.getSheet("Commande n°" + order.getId());
            }

            int rowCount = 0;
            Row rowHeader = sheet.createRow(rowCount);
            int cellCount = -1;

            //Header cell
            Cell cellName = rowHeader.createCell(++cellCount);
            cellName.setCellValue("Produit");
            Cell cellOrderedQuantity = rowHeader.createCell(++cellCount);
            cellOrderedQuantity.setCellValue("Quantité commandé");
            Cell cellMoq = rowHeader.createCell(++cellCount);
            cellMoq.setCellValue("Moq");
            Cell cellActivePrice = rowHeader.createCell(++cellCount);
            cellActivePrice.setCellValue("Prix unité");

            float ht = 0;

            //Body cell
            for (CommandLine line : order.getLines()) {
                cellCount = -1;
                Row rowByProduct = sheet.createRow(++rowCount);
                Cell cell1 = rowByProduct.createCell(++cellCount);
                cell1.setCellValue(line.getProduct().getName());
                Cell cell2 = rowByProduct.createCell(++cellCount);
                cell2.setCellValue(line.getOrderedQuantity());
                Cell cell3 = rowByProduct.createCell(++cellCount);
                cell3.setCellValue(line.getProduct().getMoq()
                                    + " " + line.getProduct().getQuantityUnity());
                Cell cell4 = rowByProduct.createCell(++cellCount);
                cell4.setCellValue(line.getActivePrice()
                        + "€ " + line.getProduct().getQuantityUnity());
                ht += line.getActivePrice();
                //todo TVA
            }
            Row rowTotal = sheet.createRow(++rowCount);
            Cell cellTotalTitre = rowTotal.createCell(++cellCount);
            cellTotalTitre.setCellValue("Prix HT");
            Cell cellTotal = rowTotal.createCell(++cellCount);
            cellTotal.setCellValue(ht + "€");

            outputStream = new FileOutputStream(excelFilePath);
            autoSizeColumns(workbook);
            workbook.write(outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                inputStream.close();
                workbook.close();
                outputStream.close();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return "redirect:orderes";
    }

    public void autoSizeColumns(Workbook workbook) {
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            if (sheet.getPhysicalNumberOfRows() > 0) {
                Row row = sheet.getRow(sheet.getFirstRowNum());
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    sheet.autoSizeColumn(columnIndex);
                }
            }
        }
    }
}