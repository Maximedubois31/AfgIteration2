package fr.afg.iteration1.service;

import fr.afg.iteration1.delegate.TvaDelegateImpl;
import fr.afg.iteration1.entity.CommandLine;
import fr.afg.iteration1.entity.PurchaseOrder;
import fr.afg.iteration1.ui.response.CalculeTvaResponse;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Iterator;

/**
 * The type Excel service.
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private TvaDelegateImpl tvaDeleg;

    @Override
    public void creerExcel(HttpSession session, PurchaseOrder order) throws IOException {

        //Remove space from company name
        String[] companyNameWithoutSpace = order.getCreator()
                                                .getCompany()
                                                .getCompanyName()
                                                .split(" ");

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < companyNameWithoutSpace.length; ++i) {
            builder.append(companyNameWithoutSpace[i]);
        }
        String companyName = builder.toString();
        String windowsPath = "C:\\AFG";
        String linuxPath = "/afg";
        String actualPathToAfG = "";
        String SE = System.getProperty("os.name").toLowerCase();

        if (SE.indexOf("win") >= 0) {
            actualPathToAfG = windowsPath;
            File dossier = new File(actualPathToAfG);
            if(!dossier.exists()){
                dossier.mkdir();
            }
        } else if (SE.indexOf("nux") >= 0) {
            actualPathToAfG = linuxPath;
            File dossier = new File(actualPathToAfG);
            if(!dossier.exists()){
                dossier.mkdir();
            }
        }

        String excelFilePath = "C:\\AFG\\" + companyName + ".xls";
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
            Cell cellPriceTva = rowHeader.createCell(++cellCount);
            cellPriceTva.setCellValue("TTC");

            float ht = 0;
            float ttc = 0;

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
                Cell cell5 = rowByProduct.createCell(++cellCount);
                Double activePrice = (double) line.getActivePrice();
                CalculeTvaResponse response = tvaDeleg.getCalculeTva(activePrice, line.getProduct()
                                                                                        .getProductType()
                                                                                        .getVatType()
                                                                                        .getId());
                cell5.setCellValue(response.getFinalPrice() + "€ " + line.getProduct().getQuantityUnity());
                ttc += response.getFinalPrice();
            }
            //Prix HT
            Row rowHT = sheet.createRow(++rowCount);
            Cell cellTotalTitre = rowHT.createCell(++cellCount);
            cellTotalTitre.setCellValue("Prix HT");
            Cell cellTotal = rowHT.createCell(++cellCount);
            cellTotal.setCellValue(ht + "€");

            //Prix TTC
            Row rowTTC = sheet.createRow(++rowCount);
            Cell cellTTCTitre = rowTTC.createCell(--cellCount);
            cellTTCTitre.setCellValue("Prix TTC");
            Cell cellTTC = rowTTC.createCell(++cellCount);
            cellTTC.setCellValue(ttc + "€");

            outputStream = new FileOutputStream(excelFilePath);
            autoSizeColumns(workbook);
            workbook.write(outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if(inputStream != null) {
                    inputStream.close();
                }
                if(workbook != null) {
                    workbook.close();
                }
                if(outputStream != null) {
                    outputStream.close();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Auto size columns.
     *
     * @param workbook the workbook
     */
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
