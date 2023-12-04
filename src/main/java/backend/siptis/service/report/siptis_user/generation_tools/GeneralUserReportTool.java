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

public class GeneralUserReportTool {

    public static String generateReport(List<SiptisUser> users, String currentRole) {
        String role = getRoleName(currentRole);
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = today.format(dateTimeFormatter);
        String fileName = "usuarios-" + role.toLowerCase() + date + ".xlsx";

        try (Workbook report = new XSSFWorkbook()) {
            Sheet sheet = report.createSheet("Reporte usuarios " + role);
            Row row = sheet.createRow(2);

            String title = "Reporte usuarios " + role;
            ReportFunctions.addCellInRow(2, title, ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(3, "Fecha " + date, ReportFunctions.getContentStyle(report), row);

            row = sheet.createRow(4);

            int rowIndex = 5;

            ReportFunctions.addCellInRow(1, "N°", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(2, "Apellidos", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(3, "Nombres", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(4, "Correo electrónico", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(5, "Ci", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(6, "Rol", ReportFunctions.getHeaderCellStyle(report), row);

            rowIndex = addUsers(users, report, rowIndex, sheet, row, role);

            for (int i = 0; i < 8; i++) {
                sheet.autoSizeColumn(i);
            }

            rowIndex = rowIndex + 2;
            row = sheet.createRow(rowIndex);
            ReportFunctions.addCellInRow(2, role.toUpperCase() + ": " + users.size(), ReportFunctions.getHeaderCellStyle(report), row);


            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            report.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException exception) {
            return null;
        }
        return fileName;
    }

    private static int addUsers(List<SiptisUser> users, Workbook report, int rowIndex, Sheet sheet, Row row, String role) {
        UserInformation userInformation;
        String names;
        String lastNames;
        String ci;
        String email;

        for (SiptisUser user : users) {
            userInformation = user.getUserInformation();
            names = userInformation.getNames();
            lastNames = userInformation.getLastNames();
            ci = userInformation.getCi();
            email = user.getEmail();

            row = sheet.createRow(rowIndex);
            ReportFunctions.addCellInRow(1, "" + (rowIndex - 4), ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(2, lastNames, ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(3, names, ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(4, email, ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(5, ci, ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(6, role, ReportFunctions.getContentStyle(report), row);

            rowIndex++;
        }
        return rowIndex;
    }

    private static String getRoleName(String role) {
        return switch (role) {
            case "TEACHER" -> "Docente";
            case "TRIBUNAL" -> "Tribunal";
            case "TUTOR" -> "Tutor";
            case "SUPERVISOR" -> "Supervisor";
            case "REVIEWER" -> "Revisor";
            default -> "";
        };
    }
}
