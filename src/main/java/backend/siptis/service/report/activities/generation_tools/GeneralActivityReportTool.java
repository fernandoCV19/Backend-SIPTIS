package backend.siptis.service.report.activities.generation_tools;

import backend.siptis.model.entity.notifications.GeneralActivity;
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

public class GeneralActivityReportTool {

    private GeneralActivityReportTool() {
    }

    public static String generateReport(List<GeneralActivity> activities) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = today.format(dateTimeFormatter);
        String fileName = "actividades-generales-" + date + ".xlsx";

        try (Workbook report = new XSSFWorkbook()) {
            Sheet sheet = report.createSheet("Proyecto por tribunales");
            Row row = sheet.createRow(2);

            String title = "Reporte de actividades generales";
            ReportFunctions.addCellInRow(2, title, ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(3, "Fecha " + date, ReportFunctions.getContentStyle(report), row);

            row = sheet.createRow(4);

            ReportFunctions.addCellInRow(1, "N°", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(2, "Actividad", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(3, "Fecha", ReportFunctions.getHeaderCellStyle(report), row);

            int rowIndex = 5;

            for (GeneralActivity activity : activities) {
                String activityName = activity.getActivityName();
                String activityDate = activity.getActivityDate().toString();

                row = sheet.createRow(rowIndex);

                ReportFunctions.addCellInRow(1, "" + (rowIndex - 4), ReportFunctions.getContentStyle(report), row);
                ReportFunctions.addCellInRow(2, activityName, ReportFunctions.getContentStyle(report), row);
                ReportFunctions.addCellInRow(3, activityDate, ReportFunctions.getContentStyle(report), row);
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
