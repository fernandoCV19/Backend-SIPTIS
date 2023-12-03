package backend.siptis.service.report.semester_information.generation_tools;

import backend.siptis.model.entity.semester.SemesterInformation;
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

public class SemesterInformationReportTool {
    private SemesterInformationReportTool() {
    }

    public static String generateReport(List<SemesterInformation> semesterInformationList) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = today.format(dateTimeFormatter);
        String fileName = "informacion-semestre-" + date + ".xlsx";

        try (Workbook report = new XSSFWorkbook()) {
            Sheet sheet = report.createSheet("semester-information");
            Row row = sheet.createRow(2);

            String title = "Reporte informacion del semestre";
            ReportFunctions.addCellInRow(2, title, ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(3, "Fecha " + date, ReportFunctions.getContentStyle(report), row);

            row = sheet.createRow(4);

            ReportFunctions.addCellInRow(1, "N°", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(2, "Inicio", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(3, "Fin", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(4, "Periodo", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(5, "Estado", ReportFunctions.getHeaderCellStyle(report), row);

            int rowIndex = 5;

            for (SemesterInformation semesterInformation : semesterInformationList) {
                row = sheet.createRow(rowIndex);

                String estado = semesterInformation.isInProgress() ? "EN CURSO" : "FINALIZADO";
                ReportFunctions.addCellInRow(1, "" + (rowIndex - 4), ReportFunctions.getContentStyle(report), row);
                ReportFunctions.addCellInRow(2, semesterInformation.getStartDate().toString(), ReportFunctions.getContentStyle(report), row);
                ReportFunctions.addCellInRow(3, semesterInformation.getEndDate().toString(), ReportFunctions.getContentStyle(report), row);
                ReportFunctions.addCellInRow(4, semesterInformation.getPeriod(), ReportFunctions.getContentStyle(report), row);
                ReportFunctions.addCellInRow(5, estado, ReportFunctions.getContentStyle(report), row);

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
