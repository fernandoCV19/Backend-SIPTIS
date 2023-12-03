package backend.siptis.service.report.geneartion_tools;

import backend.siptis.model.entity.presentations.Review;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReviewSummaryReportTool {

    private ReviewSummaryReportTool() {
    }

    public static String generateReport(String id, String specificDate, List<Review> reviews) {
        Document document = new Document(PageSize.LETTER, 85, 85, 70, 70);
        String fileName = "resumen-revisiones" + id + specificDate + ".pdf";
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            Paragraph reportField = new Paragraph();
            reportField.setAlignment(Element.ALIGN_LEFT);
            reportField.setIndentationRight(50);
            reportField.add(new Phrase("Fecha de Presentación:" + specificDate, FontFactory.getFont(FontFactory.HELVETICA, 11)));
            document.add(reportField);

            Paragraph intro = new Paragraph();
            intro.add(new Phrase("Resumen de revisiones", FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
            intro.setSpacingBefore(30);
            document.add(intro);

            for (Review review : reviews) {
                String author = review.getSiptisUser().getFullName();
                String date = review.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                String documentPath = "Documento no disponible";
                if (review.getDocumentPath() != null) {
                    documentPath = "Documento disponible, verificar en el sistema";
                }
                String comment = review.getCommentary();

                Paragraph reviewTitle = new Paragraph();
                reviewTitle.add(new Phrase("Autor: " + author, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
                reviewTitle.add(new Phrase("\nFecha de revisión: " + date, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
                reviewTitle.add(new Phrase("\n" + documentPath, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
                reviewTitle.add(new Phrase("\nComentario de la revisión:", FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
                reviewTitle.setSpacingBefore(30);
                document.add(reviewTitle);
                Paragraph body = new Paragraph();
                body.add(new Phrase(comment, FontFactory.getFont(FontFactory.HELVETICA, 11)));
                body.setSpacingBefore(10);
                document.add(body);

            }

        } catch (DocumentException | IOException de) {
            return null;
        }
        document.close();
        return fileName;
    }

}
