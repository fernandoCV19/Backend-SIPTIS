package backend.siptis.service.report.geneartion_tools;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.editors_and_reviewers.ProjectTribunal;
import backend.siptis.model.entity.project_management.Project;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

public class TribunalProjectReportTool {

    private TribunalProjectReportTool(){}

    public static String generateReport(List<Project> projects)  {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = today.format(dateTimeFormatter);
        String fileName = "proyectos-tribunal" + date + ".xlsx";

        try(Workbook report = new XSSFWorkbook()){
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
            Cell cell = row.createCell(2);
            cell.setCellValue("Reporte general de los tribunales por proyecto");
            cell = row.createCell(3);
            cell.setCellValue("Fecha: " + date);

            row = sheet.createRow(4);
            cell = row.createCell(1);
            cell.setCellValue("N°");
            cell.setCellStyle(headerStyle);
            cell = row.createCell(2);
            cell.setCellValue("Proyecto");
            cell.setCellStyle(headerStyle);
            cell = row.createCell(3);
            cell.setCellValue("Tribunales");
            cell.setCellStyle(headerStyle);

            int rowIndex = 5;
            for (Project project: projects){
                String projectName = project.getName();

                Collection<ProjectTribunal> tribunals = project.getTribunals();

                List<String> tribunalNames = tribunals
                        .stream()
                        .map(projectTribunal -> projectTribunal.getTribunal().getFullName()).toList();

                String names = String.join(", ", tribunalNames);

                row = sheet.createRow(rowIndex);

                cell = row.createCell(1);
                cell.setCellValue(rowIndex - 4);
                cell.setCellStyle(contentStyle);

                cell = row.createCell(2);
                cell.setCellValue(projectName);
                cell.setCellStyle(contentStyle);
                cell = row.createCell(3);
                cell.setCellValue(names);
                cell.setCellStyle(contentStyle);
                rowIndex++;
            }

            for (int i = 0; i < 8; i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            report.write(fileOutputStream);
            fileOutputStream.close();
        }
        catch (IOException exception) {
            return null;
        }
        return fileName;
    }
}
