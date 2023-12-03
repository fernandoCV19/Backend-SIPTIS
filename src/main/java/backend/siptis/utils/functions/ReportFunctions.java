package backend.siptis.utils.functions;

import org.apache.poi.ss.usermodel.*;

public class ReportFunctions {

    public static CellStyle getHeaderCellStyle(Workbook report) {
        CellStyle headerStyle = report.createCellStyle();
        Font font = report.createFont();
        font.setBold(true);
        headerStyle.setFont(font);
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return headerStyle;
    }

    public static CellStyle getContentStyle(Workbook report) {
        CellStyle contentStyle = report.createCellStyle();
        contentStyle.setAlignment(HorizontalAlignment.CENTER);
        contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return contentStyle;
    }

    public static CellStyle getListStyle(Workbook report) {
        CellStyle listStyle = report.createCellStyle();
        listStyle.setAlignment(HorizontalAlignment.CENTER);
        listStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        listStyle.setWrapText(true);
        return listStyle;
    }

    public static void addCellInRow(int column, String value, CellStyle cellStyle, Row row) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
        cell.setCellStyle(cellStyle);
    }
}
