package backend.siptis.service.report.projects.generation_tools;

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
import java.util.Map;
import java.util.stream.Collectors;

public class ProjectByPhaseReportTool {

    private ProjectByPhaseReportTool() {
    }

    public static String generateReport(List<Project> projects) {

        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = today.format(dateTimeFormatter);
        String fileName = "proyectos-fases" + date + ".xlsx";

        try (Workbook report = new XSSFWorkbook()) {
            Sheet sheet = report.createSheet("Reporte fases");
            Row row = sheet.createRow(2);

            String title = "Reporte proyectos por fases";
            ReportFunctions.addCellInRow(2, title, ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(3, "Fecha " + date, ReportFunctions.getContentStyle(report), row);

            row = sheet.createRow(4);

            int rowIndex = 5;

            ReportFunctions.addCellInRow(1, "N°", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(2, "Nombre Proyecto", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(3, "Fase", ReportFunctions.getHeaderCellStyle(report), row);

            rowIndex = addProjects(projects, report, rowIndex, sheet, row);

            for (int i = 0; i < 8; i++) {
                sheet.autoSizeColumn(i);
            }

            Map<String, Long> countByPhases = projects.stream()
                    .collect(Collectors.groupingBy(Project::getPhase, Collectors.counting()));

            for (Map.Entry<String, Long> values : countByPhases.entrySet()) {
                String key = values.getKey();
                Long value = values.getValue();
                rowIndex++;
                row = sheet.createRow(rowIndex);
                ReportFunctions.addCellInRow(2, key + " " + value, ReportFunctions.getHeaderCellStyle(report), row);

            }

            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            report.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException exception) {
            return null;
        }
        return fileName;
    }

    private static int addProjects(List<Project> projects, Workbook report, int rowIndex, Sheet sheet, Row row) {
        for (Project project : projects) {
            String projectName = project.getName();
            String phase = project.getPhase();

            row = sheet.createRow(rowIndex);
            ReportFunctions.addCellInRow(1, "" + (rowIndex - 4), ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(2, projectName, ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(3, phase, ReportFunctions.getContentStyle(report), row);
            rowIndex++;
        }

        return rowIndex;
    }


}
