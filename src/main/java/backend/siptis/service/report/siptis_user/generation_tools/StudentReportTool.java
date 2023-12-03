package backend.siptis.service.report.siptis_user.generation_tools;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.project_management.Project;
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

public class StudentReportTool {

    public static String generateReport(List<SiptisUser> usersSIS, List<SiptisUser> usersINF) {

        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = today.format(dateTimeFormatter);
        String fileName = "reporte-estudiantes" + date + ".xlsx";

        try (Workbook report = new XSSFWorkbook()) {
            Sheet sheet = report.createSheet("Reporte estudiantes");
            Row row = sheet.createRow(2);

            String title = "Reporte estudiantes";
            ReportFunctions.addCellInRow(2, title, ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(3, "Fecha " + date, ReportFunctions.getContentStyle(report), row);

            row = sheet.createRow(4);

            int rowIndex = 5;

            ReportFunctions.addCellInRow(1, "N°", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(2, "Apellidos", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(3, "Nombres", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(4, "Ci", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(5, "Código SIS", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(6, "Carrera", ReportFunctions.getHeaderCellStyle(report), row);

            rowIndex = addUsers(usersSIS, report, rowIndex, sheet, row);
            rowIndex = addUsers(usersINF, report, rowIndex, sheet, row);

            for (int i = 0; i < 8; i++) {
                sheet.autoSizeColumn(i);
            }

            rowIndex = rowIndex + 2;
            row = sheet.createRow(rowIndex);
            ReportFunctions.addCellInRow(2, "SISTEMAS: " + usersSIS.size(), ReportFunctions.getHeaderCellStyle(report), row);

            rowIndex++;
            row = sheet.createRow(rowIndex);
            ReportFunctions.addCellInRow(2, "INFORMATICA: " + usersINF.size(), ReportFunctions.getHeaderCellStyle(report), row);

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
        String names;
        String lastNames;
        String ci;
        String codSIS;
        String career = "";

        for (SiptisUser student : users) {
            career = "";
            userInformation = student.getUserInformation();
            names = userInformation.getNames();
            lastNames = userInformation.getLastNames();
            ci = userInformation.getCi();
            codSIS = userInformation.getCodSIS();
            if(!student.getCareer().stream().findFirst().isEmpty()){
                career = student.getCareer().stream().findFirst().get().getName();
            }

            row = sheet.createRow(rowIndex);
            ReportFunctions.addCellInRow(1, "" + (rowIndex - 4), ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(2, lastNames, ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(3, names, ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(4, ci, ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(5, codSIS, ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(6, career, ReportFunctions.getContentStyle(report), row);


            rowIndex++;
        }

        return rowIndex;
    }
}
