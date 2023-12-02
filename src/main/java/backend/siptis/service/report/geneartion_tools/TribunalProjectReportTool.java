package backend.siptis.service.report.geneartion_tools;

import backend.siptis.model.entity.editors_and_reviewers.ProjectTribunal;
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
import java.util.Collection;
import java.util.List;

public class TribunalProjectReportTool {

    private TribunalProjectReportTool() {
    }

    public static String generateReport(List<Project> projects) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = today.format(dateTimeFormatter);
        String fileName = "proyectos-tribunal" + date + ".xlsx";

        try (Workbook report = new XSSFWorkbook()) {
            Sheet sheet = report.createSheet("Tribunales de un Proyecto");
            Row row = sheet.createRow(2);

            String title = "Reporte general de los tribunales por proyecto";
            ReportFunctions.addCellInRow(2, title, ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(3, "Fecha " + date, ReportFunctions.getContentStyle(report), row);

            row = sheet.createRow(4);

            ReportFunctions.addCellInRow(1, "N°", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(2, "Proyecto", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(3, "Tribunales", ReportFunctions.getHeaderCellStyle(report), row);

            int rowIndex = 5;
            for (Project project : projects) {
                String projectName = project.getName();

                Collection<ProjectTribunal> tribunals = project.getTribunals();

                List<String> tribunalNames = tribunals
                        .stream()
                        .map(projectTribunal -> projectTribunal.getTribunal().getFullName()).toList();

                String names = String.join(", ", tribunalNames);

                row = sheet.createRow(rowIndex);

                ReportFunctions.addCellInRow(1, "" + (rowIndex - 4), ReportFunctions.getContentStyle(report), row);
                ReportFunctions.addCellInRow(2, projectName, ReportFunctions.getContentStyle(report), row);
                ReportFunctions.addCellInRow(3, names, ReportFunctions.getContentStyle(report), row);
                rowIndex++;
            }

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
}
