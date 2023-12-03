package backend.siptis.service.report.geneartion_tools;

import backend.siptis.model.entity.presentations.Presentation;
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

public class PresentationReportTool {

    private PresentationReportTool() {
    }

    public static String generateReport(String project, List<Presentation> presentations) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = today.format(dateTimeFormatter);
        String fileName = "lista-presentaciones-" + project + date + ".xlsx";
        fileName = fileName.replaceAll(" ", "");

        try (Workbook report = new XSSFWorkbook()) {
            CellStyle headerStyle = ReportFunctions.getHeaderCellStyle(report);
            CellStyle contentStyle = ReportFunctions.getContentStyle(report);
            CellStyle listStyle = ReportFunctions.getListStyle(report);

            Sheet sheet = report.createSheet("Presentaciones");
            Row row = sheet.createRow(2);
            ReportFunctions.addCellInRow(4, "Reporte de presentaciones creadas", null, row);
            ReportFunctions.addCellInRow(5, "Proyecto: " + project, null, row);
            ReportFunctions.addCellInRow(6, "Fecha: " + date, null, row);

            row = sheet.createRow(4);
            ReportFunctions.addCellInRow(1, "N°", headerStyle, row);
            ReportFunctions.addCellInRow(2, "Fecha de prsentación", headerStyle, row);
            ReportFunctions.addCellInRow(3, "Fase de prsentación", headerStyle, row);
            ReportFunctions.addCellInRow(4, "Libro azul", headerStyle, row);
            ReportFunctions.addCellInRow(5, "Software (Proyecto)", headerStyle, row);
            ReportFunctions.addCellInRow(6, "Revisado", headerStyle, row);
            ReportFunctions.addCellInRow(7, "# Revisiones", headerStyle, row);
            ReportFunctions.addCellInRow(8, "Autores de revisiones", headerStyle, row);

            int rowIndex = 5;
            for (Presentation presentation : presentations) {
                String presentationDate = presentation.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                String phase = presentation.getPhase();
                String blueBook = "No disponible";
                if (presentation.getBlueBookPath() != null) blueBook = "Disponible en el sistema";
                String projectPath = "No disponible";
                if (presentation.getProjectPath() != null) blueBook = "Disponible en el sistema";
                String reviewed = "No revisado";
                String reviewQuantity = "0";
                String authors = "";
                if (presentation.getReviewed()) {
                    reviewed = "Revisado";
                    List<String> reviewers = presentation.getReviews()
                            .stream()
                            .map(review -> review.getSiptisUser().getUserInformation().getFullName()).toList();
                    reviewQuantity = presentation.getReviews().size() + "";
                    authors = String.join("\n", reviewers);
                }

                row = sheet.createRow(rowIndex);

                ReportFunctions.addCellInRow(1, "" + (rowIndex - 4), contentStyle, row);
                ReportFunctions.addCellInRow(2, presentationDate, contentStyle, row);
                ReportFunctions.addCellInRow(3, phase, contentStyle, row);
                ReportFunctions.addCellInRow(4, blueBook, contentStyle, row);
                ReportFunctions.addCellInRow(5, projectPath, contentStyle, row);
                ReportFunctions.addCellInRow(6, reviewed, contentStyle, row);
                ReportFunctions.addCellInRow(7, reviewQuantity, contentStyle, row);
                ReportFunctions.addCellInRow(8, authors, listStyle, row);
                rowIndex++;
            }
            for (int i = 0; i < 9; i++) {
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
