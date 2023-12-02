package backend.siptis.service.report.geneartion_tools;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.user_data.UserInformation;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class  GeneralTribunalReportTool {

    private GeneralTribunalReportTool(){}

    public static String generateReport(List<SiptisUser> tribunals) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = today.format(dateTimeFormatter);
        String fileName = "lista-tribunales" + date + ".xlsx";

        try (Workbook report = new XSSFWorkbook()) {
            CellStyle headerStyle = report.createCellStyle();
            Font font = report.createFont();
            font.setBold(true);
            headerStyle.setFont(font);
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            CellStyle contentStyle = report.createCellStyle();
            contentStyle.setAlignment(HorizontalAlignment.CENTER);

            Sheet sheet = report.createSheet("Tribunales");
            Row row = sheet.createRow(2);
            Cell cell = row.createCell(4);
            cell.setCellValue("Reporte general de tribunales");
            cell = row.createCell(5);
            cell.setCellValue("Fecha: " + date);

            row = sheet.createRow(4);
            cell = row.createCell(1);
            cell.setCellValue("N°");
            cell.setCellStyle(headerStyle);
            cell = row.createCell(2);
            cell.setCellValue("Código SIS");
            cell.setCellStyle(headerStyle);
            cell = row.createCell(3);
            cell.setCellValue("Apellidos y nombres");
            cell.setCellStyle(headerStyle);
            cell = row.createCell(4);
            cell.setCellValue("Correo electrónico");
            cell.setCellStyle(headerStyle);
            cell = row.createCell(5);
            cell.setCellValue("Celular");
            cell.setCellStyle(headerStyle);
            cell = row.createCell(6);
            cell.setCellValue("# Proyectos");
            cell.setCellStyle(headerStyle);
            cell = row.createCell(7);
            cell.setCellValue("Proyectos");
            cell.setCellStyle(headerStyle);

            int rowIndex = 5;
            for (SiptisUser tribunal : tribunals) {
                UserInformation info = tribunal.getUserInformation();
                String codSIS = info.getCodSIS();
                String fullName = info.getLastNames() + ' ' + info.getNames();
                String email = tribunal.getEmail();
                String cellphone = info.getCelNumber();
                int projectQuantity = 0;
                String projectNames = "--Ningún proyecto asignado--";
                if (!tribunal.getTribunalOf().isEmpty()) {
                    List<String> projects = tribunal.getTribunalOf()
                            .stream()
                            .map(projectTribunal -> projectTribunal.getProject().getName()).toList();
                    projectQuantity = projects.size();
                    projectNames = String.join(", ", projects);
                }
                row = sheet.createRow(rowIndex);

                cell = row.createCell(1);
                cell.setCellValue(rowIndex - 4);
                cell.setCellStyle(contentStyle);
                cell = row.createCell(2);
                cell.setCellValue(codSIS);
                cell.setCellStyle(contentStyle);
                cell = row.createCell(3);
                cell.setCellValue(fullName);
                cell = row.createCell(4);
                cell.setCellValue(email);
                cell.setCellStyle(contentStyle);
                cell = row.createCell(5);
                cell.setCellValue(cellphone);
                cell.setCellStyle(contentStyle);
                cell = row.createCell(6);
                cell.setCellValue(projectQuantity);
                cell.setCellStyle(contentStyle);
                cell = row.createCell(7);
                cell.setCellValue(projectNames);
                rowIndex++;
            }
            for (int i = 0; i < 8; i++) {
                sheet.autoSizeColumn(i);
            }
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            report.write(fileOutputStream);
            fileOutputStream.close();
            return fileName;
        } catch (IOException exception) {
            return null;
        }
    }

}
