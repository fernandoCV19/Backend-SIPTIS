package backend.siptis.service.documents;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.document.ReportDocumentDTO;
import backend.siptis.service.projectManagement.CloudManagementService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

@Service
@RequiredArgsConstructor
public class DocumentGeneratorServiceImpl {
    @Autowired
    private CloudManagementService nube;

    public ServiceAnswer generateReport (ReportDocumentDTO data){
       String filename = reportGenerationTool(data) ;
       return null;
    }

    public String reportGenerationTool (ReportDocumentDTO textData){
        Document document = new Document(PageSize.LETTER, 85, 85, 70, 70);
        String fileName = textData.getPostulant() + "Report" + textData.getReportNumber() + ".pdf";
        try {
            Path tempDir  = Files.createTempDirectory("directory");
            Path tempFilePath = Paths.get(tempDir.toString(), "imgPrep.png");
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            Paragraph reportNumber = new Paragraph();
            reportNumber.setAlignment(Element.ALIGN_RIGHT);
            reportNumber.setIndentationRight(50);
            reportNumber.add (new Phrase("Informe N°.:",FontFactory.getFont(FontFactory.HELVETICA,11)));
            document.add(reportNumber);

            Table data = new Table(2);
            data.setWidth(100);
            data.setPadding(2);
            data.setBorderWidth(1);

            Cell cell = new Cell("Título: ");
            data.addCell(cell);
            cell = new Cell(textData.getTitle());
            data.addCell(cell);
            cell = new Cell("Nombre completo del postulante:");
            data.addCell(cell);
            cell = new Cell(textData.getPostulant());
            data.addCell(cell);
            cell = new Cell("Nombre del tutor:");
            data.addCell(cell);
            cell = new Cell(textData.getTutor());
            data.addCell(cell);

            cell = new Cell("Nombre del docente:");
            data.addCell(cell);
            cell = new Cell(textData.getTeacher());
            data.addCell(cell);

            cell = new Cell("Fecha de entrega:");
            data.addCell(cell);
            String date = LocalDate.now().toString();
            cell = new Cell(date);
            data.addCell(cell);
            document.add(data);

            Paragraph signatureLabels = new Paragraph();
            signatureLabels.add (new Phrase("Firma Tutor                                                  Firma Docente",FontFactory.getFont(FontFactory.HELVETICA,11)));
            signatureLabels.setSpacingBefore(85);
            signatureLabels.setAlignment(Element.ALIGN_CENTER);
            document.add(signatureLabels);


            //Image tutorSign = imagePresetForDocument(textData.getTutorSignature(), tempFilePath, 180, 50, 105, 490);
            //document.add(tutorSign);

            //Image teacherSign = imagePresetForDocument(textData.getTeacherSignature(), tempFilePath, 180, 50, 325, 490);
            //document.add(teacherSign);

            Paragraph descriptionLabel = new Paragraph();
            descriptionLabel.add (new Phrase("Descripción de lo avanzado",FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD)));
            descriptionLabel.setSpacingBefore(30);
            document.add(descriptionLabel);

            Paragraph description = new Paragraph();
            description.add (new Phrase(textData.getDescription(),FontFactory.getFont(FontFactory.HELVETICA,11)));
            description.setSpacingBefore(10);
            document.add(description);

        }
        catch(DocumentException | IOException de) {
            System.err.println(de.getMessage());
        }
        document.close();
    return fileName;
    }
    private Image imagePresetForDocument(MultipartFile image, Path tempFolder ,float width, float heigth, float x, float y) throws IOException {
        BufferedImage bImage = ImageIO.read(image.getInputStream());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos );
        byte [] datas = bos.toByteArray();
        InputStream targetStream = new ByteArrayInputStream(datas);

        FileUtils.copyInputStreamToFile(targetStream, tempFolder.toFile());
        System.out.println(tempFolder);

        Image result = Image.getInstance(tempFolder.toString());
        result.scaleAbsoluteWidth(width);
        result.scaleAbsoluteHeight(heigth);
        result.setAbsolutePosition(x,y);

        return result;
    }
}
