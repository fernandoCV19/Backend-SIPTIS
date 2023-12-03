package backend.siptis.service.report.siptis_user.generation_tools;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.user_data.UserInformation;
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

public class AdminReportTool {

    public static String generateReport(List<SiptisUser> users) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = today.format(dateTimeFormatter);
        String fileName = "reporte-administradores" + date + ".xlsx";

        try (Workbook report = new XSSFWorkbook()) {
            Sheet sheet = report.createSheet("Reporte administradores");
            Row row = sheet.createRow(2);

            String title = "Reporte administradores";
            ReportFunctions.addCellInRow(2, title, ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(3, "Fecha " + date, ReportFunctions.getContentStyle(report), row);

            row = sheet.createRow(4);
            int rowIndex = 5;

            ReportFunctions.addCellInRow(1, "N°", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(2, "Correo Electrónico", ReportFunctions.getHeaderCellStyle(report), row);

            rowIndex = addUsers(users, report, rowIndex, sheet, row);

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

    private static int addUsers(List<SiptisUser> users, Workbook report, int rowIndex, Sheet sheet, Row row) {
        UserInformation userInformation;
        String email;

        for (SiptisUser admin : users) {
            email = admin.getEmail();


            row = sheet.createRow(rowIndex);
            ReportFunctions.addCellInRow(1, "" + (rowIndex - 4), ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(2, email, ReportFunctions.getContentStyle(report), row);

            rowIndex++;
        }

        return rowIndex;
    }
}
