package backend.siptis.service.report.geneartion_tools;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.user_data.UserInformation;
import backend.siptis.utils.functions.ReportFunctions;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GeneralTribunalReportTool {

    private GeneralTribunalReportTool() {
    }

    public static String generateReport(List<SiptisUser> tribunals) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = today.format(dateTimeFormatter);
        String fileName = "lista-tribunales" + date + ".xlsx";

        try (Workbook report = new XSSFWorkbook()) {
            CellStyle headerStyle = ReportFunctions.getHeaderCellStyle(report);
            CellStyle contentStyle = ReportFunctions.getContentStyle(report);
            CellStyle listStyle = ReportFunctions.getListStyle(report);

            Sheet sheet = report.createSheet("Tribunales");
            Row row = sheet.createRow(2);
            ReportFunctions.addCellInRow(4, "Reporte general de tribunales", null, row);
            ReportFunctions.addCellInRow(5, "Fecha: " + date, null, row);

            row = sheet.createRow(4);
            ReportFunctions.addCellInRow(1, "N°", headerStyle, row);
            ReportFunctions.addCellInRow(2, "Código SIS", headerStyle, row);
            ReportFunctions.addCellInRow(3, "Apellidos y nombres", headerStyle, row);
            ReportFunctions.addCellInRow(4, "Correo electrónico", headerStyle, row);
            ReportFunctions.addCellInRow(5, "Celular", headerStyle, row);
            ReportFunctions.addCellInRow(6, "# Proyectos", headerStyle, row);
            ReportFunctions.addCellInRow(7, "Proyectos", headerStyle, row);

            int rowIndex = 5;
            for (SiptisUser tribunal : tribunals) {
                UserInformation info = tribunal.getUserInformation();
                String codSIS = info.getCodSIS();
                String fullName = info.getLastNames() + ' ' + info.getNames();
                String email = tribunal.getEmail();
                String cellphone = info.getCelNumber();
                int projectQuantity = 0;
                String projectNames = "--Ningún proyecto asignado--";
                if (!tribunal.getTribunalOf().isEmpty()) {
                    List<String> projects = tribunal.getTribunalOf()
                            .stream()
                            .map(projectTribunal -> projectTribunal.getProject().getName()).toList();
                    projectQuantity = projects.size();
                    projectNames = String.join("\n", projects);
                }
                row = sheet.createRow(rowIndex);

                ReportFunctions.addCellInRow(1, "" + (rowIndex - 4), contentStyle, row);
                ReportFunctions.addCellInRow(2, codSIS, contentStyle, row);
                ReportFunctions.addCellInRow(3, fullName, contentStyle, row);
                ReportFunctions.addCellInRow(4, email, contentStyle, row);
                ReportFunctions.addCellInRow(5, cellphone, contentStyle, row);
                ReportFunctions.addCellInRow(6, "" + projectQuantity, contentStyle, row);
                ReportFunctions.addCellInRow(7, projectNames, listStyle, row);
                rowIndex++;
            }
            for (int i = 0; i < 8; i++) {
                sheet.autoSizeColumn(i);
            }
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            report.write(fileOutputStream);
            fileOutputStream.close();
            return fileName;
        } catch (IOException exception) {
            return null;
        }
    }

}
