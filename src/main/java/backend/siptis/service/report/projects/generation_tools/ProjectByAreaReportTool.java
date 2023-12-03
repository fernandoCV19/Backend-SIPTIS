package backend.siptis.service.report.projects.generation_tools;

import backend.siptis.model.pjo.dto.report.CountItemDTO;
import backend.siptis.model.pjo.dto.report.ProjectAreaDTO;
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

public class ProjectByAreaReportTool {


    public static String generateReport(List<ProjectAreaDTO> projectsLists, List<CountItemDTO> countProjects) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = today.format(dateTimeFormatter);
        String fileName = "proyectos-por-area" + date + ".xlsx";

        try (Workbook report = new XSSFWorkbook()) {
            Sheet sheet = report.createSheet("Proyecto por areas");
            Row row = sheet.createRow(2);

            String title = "Report de proyectos por areas";
            ReportFunctions.addCellInRow(2, title, ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(3, "Fecha " + date, ReportFunctions.getContentStyle(report), row);
            row = sheet.createRow(4);
            ReportFunctions.addCellInRow(1, "N°", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(2, "Proyecto", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(3, "Area", ReportFunctions.getHeaderCellStyle(report), row);

            int rowIndex = 5;

            rowIndex = addProjectToReport(projectsLists, report, rowIndex, sheet, row);

            for (CountItemDTO item : countProjects){
                rowIndex = rowIndex + 2;
                row = sheet.createRow(rowIndex);
                ReportFunctions.addCellInRow(2, item.getItem()+ " " +item.getCount(), ReportFunctions.getHeaderCellStyle(report), row);
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

    private static int addProjectToReport(List<ProjectAreaDTO> projects, Workbook report, int rowIndex, Sheet sheet, Row row) {
        String projectName = null;
        String area = null;
        for (ProjectAreaDTO project : projects) {
            projectName = project.getProject();
            area = project.getArea();
            row = sheet.createRow(rowIndex);

            ReportFunctions.addCellInRow(1, "" + (rowIndex - 4), ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(2, projectName, ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(3, area, ReportFunctions.getContentStyle(report), row);
            rowIndex++;
        }
        return rowIndex;
    }
}
