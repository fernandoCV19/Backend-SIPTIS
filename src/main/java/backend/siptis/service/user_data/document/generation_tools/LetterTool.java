package backend.siptis.service.user_data.document.generation_tools;


import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;

public class LetterTool {

    private String[] months =
            {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

    private final static String footerText = "Sin otro particular, saludo a Ud. cordialmente.\n";
    public LetterTool() {
    }

    public String generateTribunalRequest(String student, String directorName, String career, String projectName, String teacherName) {
        String fileName = projectName + "_" + student + "_CartaConformidadDocente.pdf";
        fileName = fileName.replaceAll(" ", "");
        Document document = new Document(PageSize.LETTER, 85, 85, 70, 70);
        try {
            File file = new File(fileName);
            OutputStream outFile = new FileOutputStream(file);
            PdfWriter.getInstance(document, outFile);
            document.open();
            createWithHeader(document, directorName, career, "REF: Informe de Proyecto Final concluido y solicitud de asignación de tribunales\n");

            Paragraph bodyHeader = new Paragraph();
            bodyHeader.add(new Phrase("Estimado Director de Carrera:\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            bodyHeader.setSpacingBefore(40);

            Paragraph bodyContent = new Paragraph();
            bodyContent.setAlignment(Element.ALIGN_JUSTIFIED);
            bodyContent.add(new Phrase("Mediante la presente, en calidad de docente de la materia " +
                    "Taller de Grado II le informo que el\n" +
                    "universitario: " + student + ", está inscrito en la presente gestión. El trabajo de grado realizado por el " +
                    "mencionado universitario, lleva por título “ " + projectName + " ”, habiendo realizado el seguimiento al desarrollo del " +
                    "mismo, considero que ha sido concluido.", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            bodyContent.setSpacingBefore(50);
            document.add(bodyContent);

            Paragraph bodyEnd = new Paragraph();
            bodyEnd.add(new Phrase(footerText, FontFactory.getFont(FontFactory.HELVETICA, 10)));
            bodyEnd.setSpacingBefore(20);
            document.add(bodyEnd);

            Paragraph footer = new Paragraph();
            footer.add(new Phrase("Lic. " + teacherName + "\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            footer.add(new Phrase("Docente de Taller de Grado II", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            footer.setSpacingBefore(70);
            document.add(footer);
        } catch (Exception e) {
        }
        document.close();

        return fileName;
    }

    public String generateStudentTribunalRequest(String student, String directorName, String career, String projectName, String studentCi) {
        String fileName = projectName + "_" + student + "_SolicitudTribunalEstudiante.pdf";
        fileName = fileName.replaceAll(" ", "");
        Document document = new Document(PageSize.LETTER, 85, 85, 70, 70);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            createWithHeader(document, directorName, career, "REF: Informe de Proyecto Final concluido y solicitud de asignación de tribunales\n");

            Paragraph bodyHeader = new Paragraph();
            bodyHeader.add(new Phrase("Estimado Director de Carrera:\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            bodyHeader.setSpacingBefore(40);
            document.add(bodyHeader);

            Paragraph bodyContent = new Paragraph();
            bodyContent.setAlignment(Element.ALIGN_JUSTIFIED);
            bodyContent.add(new Phrase("Mediante la presente, solicito respetuosamente y por su intermedió, " +
                    "la designación de tribunal de defensa, ya que mi persona cumplió con todo el procedimiento y requisitos " +
                    "para la defensa del trabajo de grado titulado “ " + projectName + " ”, El mismo fue revisado por mi tutor y " +
                    "docente de taller. De tal forma, expongo a vuestra consideración para su aprobación.\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            bodyContent.setSpacingBefore(50);

            bodyContent.add(new Phrase("Con este motivo y a la espera de una respuesta positiva, me despido con las consideraciones más distinguidas.",
                    FontFactory.getFont(FontFactory.HELVETICA, 10)));
            document.add(bodyContent);

            Paragraph bodyEnd = new Paragraph();
            bodyEnd.add(new Phrase(footerText, FontFactory.getFont(FontFactory.HELVETICA, 10)));
            bodyEnd.setSpacingBefore(20);
            document.add(bodyEnd);

            Paragraph footer = new Paragraph();
            bodyContent.setAlignment(Element.ALIGN_CENTER);
            footer.add(new Phrase("Univ. " + student + "\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            footer.add(new Phrase("C.I. " + studentCi + "\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            footer.setSpacingBefore(70);
            document.add(footer);


        } catch (Exception e) {
        }
        document.close();
        return fileName;
    }

    public String generateTutorTribunalRequest(String tutor, String student, String directorName, String career, String projectName, String studentCi) throws FileNotFoundException {
        String fileName = projectName + "_" + student + "_" + tutor + "_CartaConformidadTutor.pdf";
        fileName = fileName.replaceAll(" ", "");
        Document document = new Document(PageSize.LETTER, 85, 85, 70, 70);

            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            createWithHeader(document, directorName, career, "REF: Informe de Proyecto Final concluido y solicitud de asignación de tribunales\n");

            Paragraph bodyHeader = new Paragraph();
            bodyHeader.add(new Phrase("Estimado Director de Carrera:\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            bodyHeader.setSpacingBefore(40);
            document.add(bodyHeader);

            Paragraph bodyContent = new Paragraph();
            bodyContent.setAlignment(Element.ALIGN_JUSTIFIED);
            bodyContent.add(new Phrase("Mediante la presente, en calidad de Tutor deseo informarle que el Universitario " +
                    student + " con C.I. " + studentCi + " realizó el Trabajo de grado titulado “ " + projectName
                    + " ” Habiendo concluido satisfactoriamente, solicito la asignación de tribunal para la defensa de este proyecto.", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            bodyContent.setSpacingBefore(50);

            bodyContent.add(new Phrase(" Con la mayor consideración, sin otro particular me despido.",
                    FontFactory.getFont(FontFactory.HELVETICA, 10)));
            document.add(bodyContent);

            Paragraph bodyEnd = new Paragraph();
            bodyEnd.add(new Phrase("Atentamente.\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            bodyEnd.setSpacingBefore(20);
            document.add(bodyEnd);

            Paragraph footer = new Paragraph();
            bodyContent.setAlignment(Element.ALIGN_CENTER);
            footer.add(new Phrase("Lic. " + tutor + "\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            footer.add(new Phrase("Tutor " + "\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            footer.setSpacingBefore(70);
            document.add(footer);

        document.close();
        return fileName;
    }

    public String generateSupervisorTribunalRequest(String tutor, String student, String directorName, String career, String projectName, String studentCi) throws FileNotFoundException {
        String fileName = projectName + "_" + student + "_" + tutor + "_CartaConformidadTutor.pdf";
        fileName = fileName.replaceAll(" ", "");
        Document document = new Document(PageSize.LETTER, 85, 85, 70, 70);

            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            createWithHeader(document, directorName, career, "REF: Informe de Proyecto Final concluido y solicitud de asignación de tribunales\n");

            Paragraph bodyHeader = new Paragraph();
            bodyHeader.add(new Phrase("Estimado Director de Carrera:\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            bodyHeader.setSpacingBefore(40);
            document.add(bodyHeader);

            Paragraph bodyContent = new Paragraph();
            bodyContent.setAlignment(Element.ALIGN_JUSTIFIED);
            bodyContent.add(new Phrase("Mediante la presente, en calidad de Supervisor deseo informarle que el Universitario " +
                    student + " con C.I. " + studentCi + " realizó el Trabajo de grado titulado “ " + projectName
                    + " ” Habiendo concluido satisfactoriamente, solicito la asignación de tribunal para la defensa de este proyecto.", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            bodyContent.setSpacingBefore(50);

            bodyContent.add(new Phrase(" Con la mayor consideración, sin otro particular me despido.",
                    FontFactory.getFont(FontFactory.HELVETICA, 10)));
            document.add(bodyContent);

            Paragraph bodyEnd = new Paragraph();
            bodyEnd.add(new Phrase("Atentamente.\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            bodyEnd.setSpacingBefore(20);
            document.add(bodyEnd);

            Paragraph footer = new Paragraph();
            bodyContent.setAlignment(Element.ALIGN_CENTER);
            footer.add(new Phrase("Lic. " + tutor + "\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            footer.add(new Phrase("Supervisor " + "\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            footer.setSpacingBefore(70);
            document.add(footer);

        document.close();
        return fileName;
    }

    public String generateTribunalApproval(String student, String directorName, String career, String projectName, String tribunalName) {
        String fileName = projectName + "_" + student + "_" + tribunalName + "_CartaConformidadTribunal.pdf";
        fileName = fileName.replaceAll(" ", "");
        Document document = new Document(PageSize.LETTER, 85, 85, 70, 70);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            createWithHeader(document, directorName, career, "REF: Proyecto final concluido\n");

            Paragraph bodyHeader = new Paragraph();
            bodyHeader.add(new Phrase("Estimado Director de Carrera:\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            bodyHeader.setSpacingBefore(50);
            document.add(bodyHeader);

            Paragraph bodyContent = new Paragraph();
            bodyContent.setAlignment(Element.ALIGN_JUSTIFIED);
            bodyContent.add(new Phrase("Mediante la presente, en calidad de tribunal del Proyecto Final titulado:" +
                    " “ " + projectName + " ” del postulante Univ." +
                    " " + student + ". me es grato informarle que el mencionado trabajo cumple satisfactoriamente con los " +
                    "requisitos necesarios tanto en aspectos de fondo, como de forma y contiene todos los elementos " +
                    "necesarios para la titulación en el grado de Licenciatura en Ingeniería en " + career + ". Por tal motivo, " +
                    "me permito señalar que corresponde continuar con la presentación pública respectiva.", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            bodyContent.setSpacingBefore(50);
            document.add(bodyContent);

            Paragraph bodyEnd = new Paragraph();
            bodyEnd.add(new Phrase(footerText, FontFactory.getFont(FontFactory.HELVETICA, 10)));
            bodyEnd.setSpacingBefore(20);
            document.add(bodyEnd);

            Paragraph footer = new Paragraph();
            footer.add(new Phrase("Lic. " + tribunalName + "\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            footer.add(new Phrase("Tribunal asignado", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            footer.setSpacingBefore(70);
            document.add(footer);


        } catch (Exception e) {
        }
        document.close();
        return fileName;
    }

    private void createWithHeader(Document document, String directorName, String career, String docTitle) {
        LocalDate today = LocalDate.now();
        Paragraph locationDate = new Paragraph();
        locationDate.setAlignment(Element.ALIGN_RIGHT);
        locationDate.add(
                new Phrase("Cochabamba, " + today.getDayOfMonth() + " de " + getMonthName(today.getMonthValue()) + " del " + today.getYear(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
        document.add(locationDate);

        Paragraph header = new Paragraph();
        header.add(new Phrase("Señor:", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
        header.setSpacingBefore(30);
        document.add(header);

        Paragraph director = new Paragraph();
        director.add(new Phrase("Lic. " + directorName + "\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        director.add(new Phrase("DIRECTOR de la CARRERA de Ing. " + career + "\n", FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
        director.add(new Phrase("Presente.-", FontFactory.getFont(FontFactory.HELVETICA, 11)));
        director.setSpacingBefore(10);
        document.add(director);

        Paragraph title = new Paragraph();
        title.setAlignment(Element.ALIGN_CENTER);
        title.add(new Phrase(docTitle, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD | Font.UNDERLINE)));
        title.setSpacingBefore(50);
        document.add(title);
    }

    private String getMonthName(int month) {
        return months[month - 1];
    }
}
