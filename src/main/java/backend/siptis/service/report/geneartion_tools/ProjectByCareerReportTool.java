package backend.siptis.service.report.geneartion_tools;

import backend.siptis.commons.UserCareer;
import backend.siptis.model.entity.project_management.Project;
import backend.siptis.utils.functions.ReportFunctions;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProjectByCareerReportTool {

    private ProjectByCareerReportTool() {
    }

    public static String generateReport(List<Project> sistemas, List<Project> informatica, List<Project> combined) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = today.format(dateTimeFormatter);
        String fileName = "proyectos-por-carrera" + date + ".xlsx";

        try (Workbook report = new XSSFWorkbook()) {
            Sheet sheet = report.createSheet("Proyecto por tribunales");
            Row row = sheet.createRow(2);

            String title = "Report de proyectos por carreras";
            ReportFunctions.addCellInRow(2, title, ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(3, "Fecha " + date, ReportFunctions.getContentStyle(report), row);

            row = sheet.createRow(4);

            ReportFunctions.addCellInRow(1, "N°", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(2, "Proyecto", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(3, "Carrera", ReportFunctions.getHeaderCellStyle(report), row);

            int rowIndex = 5;
            for (Project project : sistemas) {
                String projectName = project.getName();

                row = sheet.createRow(rowIndex);

                ReportFunctions.addCellInRow(1, "" + (rowIndex - 4), ReportFunctions.getContentStyle(report), row);
                ReportFunctions.addCellInRow(2, projectName, ReportFunctions.getContentStyle(report), row);
                ReportFunctions.addCellInRow(3, UserCareer.SISTEMAS.name(), ReportFunctions.getContentStyle(report), row);
            }
            rowIndex = addCareerToReport(sistemas, report, rowIndex, sheet, row, UserCareer.SISTEMAS.name());
            rowIndex = addCareerToReport(sistemas, report, rowIndex, sheet, row, UserCareer.INFORMATICA.name());
            rowIndex = addCareerToReport(sistemas, report, rowIndex, sheet, row, "COMPARTIDO");

            rowIndex = rowIndex + 2;
            row = sheet.createRow(rowIndex);
            ReportFunctions.addCellInRow(2, "INFORMATICA: " + informatica.size(), ReportFunctions.getHeaderCellStyle(report), row);

            rowIndex++;
            row = sheet.createRow(rowIndex);
            ReportFunctions.addCellInRow(2, "SISTEMAS: " + sistemas.size(), ReportFunctions.getHeaderCellStyle(report), row);

            rowIndex++;
            row = sheet.createRow(rowIndex);
            ReportFunctions.addCellInRow(2, "COMPARTIDO: " + combined.size(), ReportFunctions.getHeaderCellStyle(report), row);

            for (int i = 0; i < 8; i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            report.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException exception) {
            return null;
        }
        return fileName;
    }

    private static int addCareerToReport(List<Project> projects, Workbook report, int rowIndex, Sheet sheet, Row row, String career) {
        String projectName = null;
        for (Project project : projects) {
            projectName = project.getName();
            row = sheet.createRow(rowIndex);

            ReportFunctions.addCellInRow(1, "" + (rowIndex - 4), ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(2, projectName, ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(3, career, ReportFunctions.getContentStyle(report), row);
            rowIndex++;
        }
        return rowIndex;
    }
}
