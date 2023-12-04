package backend.siptis.service.report.tribunals.generation_tools;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.user_data.UserArea;
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

public class TribunalsReportTool {

    public static String generateReport(List<SiptisUser> tribunals) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = today.format(dateTimeFormatter);
        String fileName = "lista-tribunales" + date + ".xlsx";

        try (Workbook report = new XSSFWorkbook()) {
            CellStyle headerStyle = ReportFunctions.getHeaderCellStyle(report);
            CellStyle contentStyle = ReportFunctions.getContentStyle(report);
            Sheet sheet = report.createSheet("Tribunales");

            addHeadersValues(sheet, headerStyle, date);
            addBodyValues(sheet, contentStyle, tribunals);

            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            report.write(fileOutputStream);
            fileOutputStream.close();
            return fileName;
        } catch (IOException exception) {
            return null;
        }
    }

    private static void addBodyValues(Sheet sheet, CellStyle contentStyle, List<SiptisUser> tribunals) {
        int rowIndex = 5;
        for (SiptisUser tribunal : tribunals) {
            String name = tribunal.getFullName();
            String numberOfProjects = tribunal.getTribunalOf().size() + "";
            String projects = tribunal.getTribunalOf()
                    .stream()
                    .map(projectTribunal -> projectTribunal.getProject().getName())
                    .collect(Collectors.joining(", "));
            String areas = tribunal.getAreas()
                    .stream()
                    .map(UserArea::getName)
                    .collect(Collectors.joining(", "));

            Row row = sheet.createRow(rowIndex);

            ReportFunctions.addCellInRow(1, String.valueOf(rowIndex - 4), contentStyle, row);
            ReportFunctions.addCellInRow(2, name, contentStyle, row);
            ReportFunctions.addCellInRow(3, numberOfProjects, contentStyle, row);
            ReportFunctions.addCellInRow(4, projects, contentStyle, row);
            ReportFunctions.addCellInRow(5, areas, contentStyle, row);
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
        ReportFunctions.addCellInRow(2, "Tribunal", headerStyle, row);
        ReportFunctions.addCellInRow(3, "Num proyectos", headerStyle, row);
        ReportFunctions.addCellInRow(4, "Proyectos", headerStyle, row);
        ReportFunctions.addCellInRow(5, "Areas", headerStyle, row);
    }
}
