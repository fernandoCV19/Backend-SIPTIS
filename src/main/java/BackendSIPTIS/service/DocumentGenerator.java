package BackendSIPTIS.service;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfString;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;



public class DocumentGenerator {
    public static void main(String[] args){
        System.out.println("Document margins");        
        // step 1: creation of a document-object
        Document document = new Document(PageSize.LETTER, 85, 85, 70, 70);
        
        try {
            
            // step 2:
            // we create a writer that listens to the document
            // and directs a PDF-stream to a file
            
            PdfWriter.getInstance(document, new FileOutputStream("modeloDocente.pdf"));
            
            // step 3: we open the document
            document.open();
            
            Paragraph fecha = new Paragraph();
            fecha.setAlignment(Element.ALIGN_RIGHT);
            fecha.add (new Phrase("Cochabamba, 17 de junio del 2019",FontFactory.getFont(FontFactory.HELVETICA,10)));
            
            document.add(fecha);

            Paragraph presentación ;




            
        }
        catch(DocumentException | IOException de) {
            System.err.println(de.getMessage());
        }

        // step 5: we close the document
        document.close();
    }
}
