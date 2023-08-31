package backend.siptis.service.document.generationTools;


import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LetterTool {

    String[] months =
            {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

    public String generateTribunalRequest (String student, String directorName, String career, String projectName, String teacherName){
        Document document = new Document(PageSize.LETTER, 85, 85, 70, 70);
        // LocalDate today = LocalDate.now();
        String fileName = projectName +"_"+ student + "_TribunalSolicitud.pdf";
        fileName = fileName.replaceAll(" ","");
        try{
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            writeHeader(document, directorName, career, "REF: Informe de Proyecto Final concluido y solicitud de asignación de tribunales\n");

            Paragraph bodyHeader = new Paragraph();
            //bodyHeader.setAlignment(Element.Alig);
            bodyHeader.add (new Phrase("Señor Director:\n",FontFactory.getFont(FontFactory.HELVETICA,10)));
            bodyHeader.setSpacingBefore(50);
            document.add(bodyHeader);

            Paragraph bodyContent = new Paragraph();
            bodyContent.setAlignment(Element.ALIGN_LEFT);
            bodyContent.add (new Phrase("Mediante la presente, en calidad de docente de la materia " +
                    "Taller de Grado II le informo que el\n" +
                    "universitario: "+ student+", está inscrito en la presente gestión. El trabajo de grado realizado por el\n" +
                    "mencionado universitario, lleva por título “ "+ projectName +" ”, habiendo realizado el seguimiento al desarrollo del\n" +
                    "mismo, considero que ha sido concluido",FontFactory.getFont(FontFactory.HELVETICA,10)));
            bodyContent.setSpacingBefore(50);
            document.add(bodyContent);

            Paragraph bodyEnd = new Paragraph();
            // bodyContent.setAlignment(Element.ALIGN_JUSTIFIED);
            bodyEnd.add (new Phrase("Sin otro particular, saludo a Ud. cordialmente.\n",FontFactory.getFont(FontFactory.HELVETICA,10)));
            bodyEnd.setSpacingBefore(20);
            document.add(bodyEnd);

            Paragraph footer = new Paragraph();
            // bodyContent.setAlignment(Element.ALIGN_JUSTIFIED);
            footer.add (new Phrase(" "+ teacherName +" Ph.D.\n",FontFactory.getFont(FontFactory.HELVETICA,10)));
            footer.add (new Phrase("Docente de Taller de Grado II",FontFactory.getFont(FontFactory.HELVETICA,10)));
            footer.setSpacingBefore(70);
            document.add(footer);


        }catch (Exception e){

        }
        document.close();
        return fileName;
    }

    public  String generateTribunalApproval(String student, String directorName, String career, String projectName, String tribunalName){
        Document document = new Document(PageSize.LETTER, 85, 85, 70, 70);
        // LocalDate today = LocalDate.now();
        String fileName = projectName +"_"+ student + "_TribunalModelo.pdf";
        fileName = fileName.replaceAll(" ","");
        try{
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            writeHeader(document, directorName, career, "REF: Proyecto final concluido\n");

            Paragraph bodyHeader = new Paragraph();
            //bodyHeader.setAlignment(Element.Alig);
            bodyHeader.add (new Phrase("Señor Director:\n",FontFactory.getFont(FontFactory.HELVETICA,10)));
            bodyHeader.setSpacingBefore(50);
            document.add(bodyHeader);

            Paragraph bodyContent = new Paragraph();
            bodyContent.setAlignment(Element.ALIGN_LEFT);
            bodyContent.add (new Phrase("Mediante la presente, en calidad de tribunal del Proyecto Final titulado:" +
                    " “ "+ projectName+" ” del postulante Univ.\n" +
                    " "+student+". me es grato informarle que el mencionado trabajo cumple satisfactoriamente con los\n" +
                    "requisitos necesarios tanto en aspectos de fondo, como de forma y contiene todos los elementos\n" +
                    "necesarios para la titulación en el grado de Licenciatura en Ingeniería de Sistemas. Por tal motivo,\n" +
                    "me permito señalar que corresponde continuar con la presentación pública correspondiente.",FontFactory.getFont(FontFactory.HELVETICA,10)));
            bodyContent.setSpacingBefore(50);
            document.add(bodyContent);

            Paragraph bodyEnd = new Paragraph();
            // bodyContent.setAlignment(Element.ALIGN_JUSTIFIED);
            bodyEnd.add (new Phrase("Sin otro particular, saludo a Ud. cordialmente.\n",FontFactory.getFont(FontFactory.HELVETICA,10)));
            bodyEnd.setSpacingBefore(20);
            document.add(bodyEnd);

            Paragraph footer = new Paragraph();
            // bodyContent.setAlignment(Element.ALIGN_JUSTIFIED);
            footer.add (new Phrase("MSc. "+ tribunalName +"\n",FontFactory.getFont(FontFactory.HELVETICA,10)));
            footer.add (new Phrase("Tribunal asignado",FontFactory.getFont(FontFactory.HELVETICA,10)));
            footer.setSpacingBefore(70);
            document.add(footer);


        }catch (Exception e){

        }
        document.close();
        return fileName;
    }

    private void writeHeader(Document document, String directorName, String career, String docTitle){
        LocalDate today = LocalDate.now();
        Paragraph locationDate = new Paragraph();
        locationDate.setAlignment(Element.ALIGN_RIGHT);
        //locationDate.setIndentationRight(30);
        locationDate.add (
                new Phrase("Cochabamba, "+ today.getDayOfMonth() +" de "+ getMonthName(today.getMonthValue()) +" del " + today.getYear(),FontFactory.getFont(FontFactory.HELVETICA,10)));
        document.add(locationDate);

        Paragraph header = new Paragraph();
        header.add (new Phrase("Señor:",FontFactory.getFont(FontFactory.HELVETICA,10, Font.BOLD)));
        header.setSpacingBefore(30);
        document.add(header);

        Paragraph director = new Paragraph();
        director.add (new Phrase("Lic. "+directorName+"\n",FontFactory.getFont(FontFactory.HELVETICA,10)));
        director.add (new Phrase("DIRECTOR de la CARRERA de Ing. "+ career+"\n",FontFactory.getFont(FontFactory.HELVETICA,10, Font.BOLD)));
        director.add (new Phrase("Presente.-",FontFactory.getFont(FontFactory.HELVETICA,11)));
        director.setSpacingBefore(10);
        document.add(director);

        Paragraph title = new Paragraph();
        title.setAlignment(Element.ALIGN_CENTER);
        title.add (new Phrase(docTitle,FontFactory.getFont(FontFactory.HELVETICA,11, Font.BOLD | Font.UNDERLINE)));
        title.setSpacingBefore(50);
        document.add(title);
    }

    private String getMonthName(int month){
        return months[month-1];
    }
}
