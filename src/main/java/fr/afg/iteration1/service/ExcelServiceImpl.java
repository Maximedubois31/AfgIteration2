package fr.afg.iteration1.service;

import fr.afg.iteration1.entity.CommandLine;
import fr.afg.iteration1.entity.PurchaseOrder;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Iterator;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public void creerExcel(HttpSession session, PurchaseOrder order) throws IOException, InvalidFormatException {

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
