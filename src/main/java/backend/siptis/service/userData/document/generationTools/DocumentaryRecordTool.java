package backend.siptis.service.userData.document.generationTools;

import backend.siptis.commons.Modality;
import backend.siptis.model.pjo.dto.document.DocumentaryRecordDto;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DocumentaryRecordTool {
    private final Path location;

    public DocumentaryRecordTool(Path location) {
        this.location = location;
    }

    public String generate(DocumentaryRecordDto documentaryRecordDto, String modality, String career, String names, String lastNames, List<String> tutors, String title) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = today.format(dateTimeFormatter);
        String fileName = names + lastNames + "Ficha" + date + ".pdf";
        fileName = fileName.replaceAll(" ", "");
        try {
            Path temporal = location;
            PdfReader reader = new PdfReader(temporal.toString());
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileName));
            PdfContentByte cb = stamper.getOverContent(1);
            ColumnText cr = new ColumnText(cb);
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 13);
            cr.setSimpleColumn(75f, 30f, 600f, 678f);
            Paragraph element = new Paragraph(new Phrase(0, "X", font));
            //24.3 cm = 678f
            float degreeLocation = 142;
            element.setIndentationLeft(degreeLocation);
            cr.addElement(element);

            element = new Paragraph(new Phrase(0, "X", font));
            element.setSpacingBefore(30);
            float modalityLocation = 42;
            if (modality.equals(Modality.PROYECTO_DE_GRADO.toString())) {
                modalityLocation = 195;
            }
            if (modality.equals(Modality.TRABAJO_DIRIGIDO.toString())) {
                modalityLocation = 338;
            }
            if (modality.equals(Modality.ADSCRIPCION.toString())) {
                modalityLocation = 449;
            }
            element.setIndentationLeft(modalityLocation);
            cr.addElement(element);

            font = FontFactory.getFont(FontFactory.HELVETICA, 10);
            element = new Paragraph(new Phrase(0, modality.replaceAll("_", " "), font));
            element.setSpacingBefore(16);
            element.setIndentationLeft(160);
            cr.addElement(element);

            element = new Paragraph(new Phrase(0, "Ciencias y tecnología", font));
            element.setSpacingBefore(13);
            element.setIndentationLeft(73);
            cr.addElement(element);

            element = new Paragraph(new Phrase(0, career, font));
            element.setSpacingBefore(13);
            element.setIndentationLeft(70);
            cr.addElement(element);

            element = new Paragraph(new Phrase(0, autoIndentNames(lastNames, names), font));
            element.setSpacingBefore(38);
            element.setIndentationLeft(84);
            cr.addElement(element);

            element = new Paragraph(new Phrase(0, anidateListNames(tutors), font));
            element.setSpacingBefore(13);
            element.setIndentationLeft(84);
            cr.addElement(element);

            element = new Paragraph(new Phrase(0, documentaryRecordDto.getConsultants(), font));
            element.setSpacingBefore(12);
            element.setIndentationLeft(86);
            cr.addElement(element);

            element = new Paragraph(new Phrase(0, title.toUpperCase(), font));
            element.setSpacingBefore(13);
            element.setIndentationLeft(86);
            element.setSpacingAfter(38);
            cr.addElement(element);
            //corregir linea texto
            ArrayList<String> totalSummary = flowText(documentaryRecordDto.getSummary());
            for (String s : totalSummary) {
                element = new Paragraph(new Phrase(0, s, font));
                element.setSpacingBefore(13);
                element.setIndentationLeft(4);
                cr.addElement(element);
            }
            cr.go();

            cr = new ColumnText(cb);
            cr.setSimpleColumn(75f, 30f, 600f, 175f);

            element = new Paragraph(new Phrase(0, documentaryRecordDto.getKeyWords(), font));
            element.setSpacingBefore(62);
            element.setIndentationLeft(4);
            cr.addElement(element);
            cr.go();

            cr = new ColumnText(cb);
            cr.setSimpleColumn(75f, 30f, 600f, 145f);

            element = new Paragraph(new Phrase(0, documentaryRecordDto.getDefenseDate(), font));
            element.setSpacingBefore(11);
            element.setIndentationLeft(120);
            cr.addElement(element);

            element = new Paragraph(new Phrase(0, documentaryRecordDto.getPageQuantity(), font));
            element.setIndentationLeft(390);
            cr.addElement(element);

            cr.go();

            stamper.close();
            reader.close();
            Files.delete(temporal);
            return fileName;
        } catch (IOException de) {
            System.err.println(de.getMessage());
        }
        return fileName;
    }

    private String anidateListNames(List<String> names) {
        StringBuilder res = new StringBuilder();
        for (String s : names) {
            res.append(s).append(" ");
        }
        return res.toString().trim();
    }

    private String autoIndentNames(String lastNames, String names) {
        String[] lnParts = lastNames.split(" ", 2);
        String fLastName = lnParts[0];
        String mLastName = lnParts[1];
        String space1 = spacing(43 - fLastName.length());
        String space2 = spacing(50 - mLastName.length());

        return fLastName + space1 + mLastName + space2 + names;
    }

    private String spacing(int quantity) {
        return " ".repeat(Math.max(0, quantity));
    }

    private ArrayList<String> flowText(String text) {
        ArrayList<String> res = new ArrayList<>();
        if (text.length() < 100 && !text.contains("\n")) {
            res.add(text);
            return res;
        }
        String aux = text;
        while (aux.length() >= 100) {
            int actualIndex = 100;
            char c = aux.charAt(actualIndex);
            if (c != ' ') {
                actualIndex = lookForSpacingIndex(aux, actualIndex - 1);
            }
            String added = aux.substring(0, actualIndex);
            if (added.contains("\n")) {
                actualIndex = added.indexOf('\n');
                res.add(added.substring(0, actualIndex));
            }
            if (!added.contains("\n")) {
                res.add(added);
            }
            aux = aux.substring(actualIndex + 1);
        }
        if (aux.contains("\n")) {
            int postndex = aux.indexOf('\n');
            res.add(aux.substring(0, postndex));
        }
        if (!aux.contains("\n")) {
            res.add(aux);
        }
        return res;
    }

    private int lookForSpacingIndex(String text, int reason) {
        for (int i = reason; i > 0; i--) {
            if (text.charAt(i) == ' ' || text.charAt(i) == '\n') {
                return i;
            }
        }
        return reason;
    }
}