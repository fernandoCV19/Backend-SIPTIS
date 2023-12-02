package backend.siptis.service.report.geneartion_tools;

import backend.siptis.model.entity.defense_management.Defense;
import backend.siptis.utils.functions.ReportFunctions;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SiptisUserReportTool {

    public static String generateReport(List<Defense> defenses) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = today.format(dateTimeFormatter);
        String fileName = "lista-tutores" + date + ".xlsx";

        try (Workbook report = new XSSFWorkbook()) {
            CellStyle headerStyle = ReportFunctions.getHeaderCellStyle(report);
            CellStyle contentStyle = ReportFunctions.getContentStyle(report);
            Sheet sheet = report.createSheet("Tutores");

            addHeadersValues(sheet, headerStyle, date);
            addBodyValues(sheet, contentStyle, defenses);

            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            report.write(fileOutputStream);
            fileOutputStream.close();
            return fileName;
        } catch (IOException exception) {
            return null;
        }
    }

    private static void addBodyValues(Sheet sheet, CellStyle contentStyle, List<Defense> defenses) {
        int rowIndex = 5;
        for (Defense defense : defenses) {
            String environment = null;
            String location = null;
            String project = null;
            String students = null;
            String tribunals = null;
            String date = null;
            String hour = null;
            String score = null;
            Row row = sheet.createRow(rowIndex);

            ReportFunctions.addCellInRow(1, String.valueOf(rowIndex - 4), contentStyle, row);
            ReportFunctions.addCellInRow(2, environment, contentStyle, row);
            ReportFunctions.addCellInRow(3, location, contentStyle, row);
            ReportFunctions.addCellInRow(4, project, contentStyle, row);
            ReportFunctions.addCellInRow(5, students, contentStyle, row);
            ReportFunctions.addCellInRow(6, tribunals, contentStyle, row);
            ReportFunctions.addCellInRow(7, date, contentStyle, row);
            ReportFunctions.addCellInRow(8, hour, contentStyle, row);
            ReportFunctions.addCellInRow(9, score, contentStyle, row);
            rowIndex++;
        }
        for (int i = 0; i < 8; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private static void addHeadersValues(Sheet sheet, CellStyle headerStyle, String date) {
        Row row = sheet.createRow(2);
        ReportFunctions.addCellInRow(4, "Reporte general de usuarios segun rol", null, row);
        ReportFunctions.addCellInRow(6, "Fecha: " + date, null, row);

        row = sheet.createRow(4);
        ReportFunctions.addCellInRow(1, "N°", headerStyle, row);
        ReportFunctions.addCellInRow(2, "Ambiente", headerStyle, row);
        ReportFunctions.addCellInRow(3, "Ubicacion", headerStyle, row);
        ReportFunctions.addCellInRow(4, "Proyecto", headerStyle, row);
        ReportFunctions.addCellInRow(5, "Estudiantes", headerStyle, row);
        ReportFunctions.addCellInRow(6, "Tribunales", headerStyle, row);
        ReportFunctions.addCellInRow(7, "Fecha", headerStyle, row);
        ReportFunctions.addCellInRow(8, "Hora", headerStyle, row);
        ReportFunctions.addCellInRow(9, "Calificacion", headerStyle, row);
    }
}
