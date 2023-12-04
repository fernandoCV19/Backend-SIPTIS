package backend.siptis.service.report.defenses.generation_tools;

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
import java.util.stream.Collectors;

public class DefensesReportTool {

    public static String generateReport(List<Defense> defenses) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = today.format(dateTimeFormatter);
        String fileName = "lista-defensas" + date + ".xlsx";

        try (Workbook report = new XSSFWorkbook()) {
            CellStyle headerStyle = ReportFunctions.getHeaderCellStyle(report);
            CellStyle contentStyle = ReportFunctions.getContentStyle(report);
            Sheet sheet = report.createSheet("Defensas");

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
            String environment = defense.getPlaceToDefense().getName();
            String location = defense.getPlaceToDefense().getLocation();
            String project = defense.getProject().getName();
            String students = defense.getProject().getStudents()
                    .stream()
                    .map(projectStudent -> projectStudent.getStudent().getFullName())
                    .collect(Collectors.joining(", "));
            String tribunals = defense.getProject().getTribunals()
                    .stream()
                    .map(projectTribunal -> projectTribunal.getTribunal().getFullName())
                    .collect(Collectors.joining(", "));
            String date = defense.getDate().toString();
            String hour = defense.getStartTime() + " : " + defense.getEndTime();
            Double possibleScore = defense.getProject().getTotalDefensePoints();
            String score = possibleScore != null ? possibleScore.toString() : "Aun no defendido";

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
        for (int i = 0; i < 10; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private static void addHeadersValues(Sheet sheet, CellStyle headerStyle, String date) {
        Row row = sheet.createRow(2);
        ReportFunctions.addCellInRow(4, "Reporte general de defensas", null, row);
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
