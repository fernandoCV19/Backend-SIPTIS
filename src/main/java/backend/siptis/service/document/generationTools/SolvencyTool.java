package backend.siptis.service.document.generationTools;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import lombok.RequiredArgsConstructor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
public class SolvencyTool {
    private Path location;
    public SolvencyTool (Path location){
        this.location = location;
    }
    public String generate(String completeName, String ci, String career)  {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = today.format(dateTimeFormatter);
        String fileName =  completeName + "Solvencia"+".pdf";
        fileName = fileName.replaceAll(" ","");
        try {
            Path temporal = location;
            PdfReader reader = new PdfReader(temporal.toString());
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileName));
            PdfContentByte cb = stamper.getOverContent(1);
            ColumnText cr = new ColumnText(cb);
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 14);
            cr.setSimpleColumn(50f, 100f, 600f, 580f);
            Paragraph element = new Paragraph(new Phrase(0, completeName, font));
            element.setIndentationLeft(60);
            cr.addElement(element);
            element = new Paragraph(new Phrase(0, ci, font));
            element.setSpacingBefore(39);
            element.setIndentationLeft(35);
            cr.addElement(element);
            element = new Paragraph(new Phrase(0, date, font));
            element.setIndentationLeft(325);
            cr.addElement(element);
            element = new Paragraph(new Phrase(0, career, font));
            element.setSpacingBefore(39);
            element.setIndentationLeft(58);
            cr.addElement(element);
            cr.go();
            stamper.close();
            reader.close();
            // Files.delete(temporal);
            return fileName;
        }catch(IOException de) {
            System.err.println(de.getMessage());
        }
        return fileName;
    }
}
