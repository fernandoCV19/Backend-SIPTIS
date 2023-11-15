package backend.siptis.service.userData.document.generationTools;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
public class ReportTool {
    public String generate(String postulant, String reportNumber, String title, List<String> tutors, String teacher, String descriptionBody) {
        Document document = new Document(PageSize.LETTER, 85, 85, 70, 70);
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = today.format(dateTimeFormatter);
        String fileName = postulant + "Report" + reportNumber + ".pdf";
        fileName = fileName.replaceAll(" ", "");
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            Paragraph reportField = new Paragraph();
            reportField.setAlignment(Element.ALIGN_RIGHT);
            reportField.setIndentationRight(50);
            reportField.add(new Phrase("Informe N°.:" + reportNumber, FontFactory.getFont(FontFactory.HELVETICA, 11)));
            document.add(reportField);

            Table data = new Table(2);
            data.setWidth(100);
            data.setPadding(2);
            data.setBorderWidth(1);

            Cell cell = new Cell("Título: ");
            data.addCell(cell);
            cell = new Cell(title);
            data.addCell(cell);
            cell = new Cell("Nombre completo del postulante:");
            data.addCell(cell);
            cell = new Cell(postulant);
            data.addCell(cell);
            if (tutors.size() == 1) {
                cell = new Cell("Nombre del tutor:");
            }
            if (tutors.size() > 1) {
                cell = new Cell("Nombres de tutores:");
            }
            data.addCell(cell);
            cell = new Cell(anidateListNames(tutors));
            data.addCell(cell);

            cell = new Cell("Nombre del docente:");
            data.addCell(cell);
            cell = new Cell(teacher);
            data.addCell(cell);

            cell = new Cell("Fecha de entrega:");
            data.addCell(cell);
            cell = new Cell(date);
            data.addCell(cell);
            document.add(data);

            Paragraph descriptionLabel = new Paragraph();
            descriptionLabel.add(new Phrase("Descripción de lo avanzado", FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
            descriptionLabel.setSpacingBefore(30);
            document.add(descriptionLabel);

            Paragraph description = new Paragraph();
            description.add(new Phrase(descriptionBody, FontFactory.getFont(FontFactory.HELVETICA, 11)));
            description.setSpacingBefore(10);
            document.add(description);

        } catch (DocumentException | IOException de) {
            System.err.println(de.getMessage());
        }
        document.close();
        return fileName;
    }

    private String anidateListNames(List<String> names) {
        StringBuilder res = new StringBuilder();
        for (String s : names) {
            res.append(s).append("\n");
        }
        return res.toString().trim();
    }
}
