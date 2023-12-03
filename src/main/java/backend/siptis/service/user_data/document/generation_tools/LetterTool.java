package backend.siptis.service.user_data.document.generation_tools;


import backend.siptis.model.entity.editors_and_reviewers.ProjectTribunal;
import backend.siptis.service.notifications.notification_senders.DocumentSenderService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@RequiredArgsConstructor
public class LetterTool {

    private static final String FOOTER_TEXT = "Sin otro particular, saludo a Ud. cordialmente.\n";
    private static final String DOC_TITLE = "REF: Informe de Proyecto Final concluido y solicitud de asignación de tribunales\n";
    private static final String START_TEXT = "Estimado Director de Carrera:\n";
    private final String[] months =
            {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    private final String[] unitNumber = {"", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve"};
    String[] espNumber = {"diez", "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho", "diecinueve"};
    String[] decNumber = {"", "", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa"};
    DocumentSenderService documentSenderService;

    public LetterTool(DocumentSenderService documentSenderService){
        this.documentSenderService = documentSenderService;
    }


    public String generateTribunalRequest(String student, String email, String directorName, String career, String projectName, String teacherName) {
        String fileName = projectName + "_" + student + "_CartaConformidadDocente.pdf";
        fileName = fileName.replace(" ", "");
        Document document = new Document(PageSize.LETTER, 85, 85, 70, 70);
        File file = new File(fileName);
        try {
            OutputStream outFile = new FileOutputStream(file);
            PdfWriter.getInstance(document, outFile);
            document.open();
            createWithHeader(document, directorName, career, DOC_TITLE);

            Paragraph bodyHeader = new Paragraph();
            bodyHeader.add(new Phrase(START_TEXT, FontFactory.getFont(FontFactory.HELVETICA, 10)));
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
            bodyEnd.add(new Phrase(FOOTER_TEXT, FontFactory.getFont(FontFactory.HELVETICA, 10)));
            bodyEnd.setSpacingBefore(20);
            document.add(bodyEnd);

            Paragraph footer = new Paragraph();
            footer.add(new Phrase("Lic. " + teacherName + "\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            footer.add(new Phrase("Docente de Taller de Grado II", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            footer.setSpacingBefore(70);
            document.add(footer);
        } catch (Exception e) {
            fileName = null;
        } finally {
            document.close();
            documentSenderService.sendDocument(fileName, email, file);
        }
        return fileName;
    }

    public String generateStudentTribunalRequest(String student, String email, String directorName, String career, String projectName, String studentCi) {
        String fileName = projectName + "_" + student + "_SolicitudTribunalEstudiante.pdf";
        fileName = fileName.replace(" ", "");
        Document document = new Document(PageSize.LETTER, 85, 85, 70, 70);
        File file = new File(fileName);
        try {
            OutputStream outFile = new FileOutputStream(file);
            PdfWriter.getInstance(document, outFile);
            document.open();
            createWithHeader(document, directorName, career, DOC_TITLE);

            Paragraph bodyHeader = new Paragraph();
            bodyHeader.add(new Phrase(START_TEXT, FontFactory.getFont(FontFactory.HELVETICA, 10)));
            bodyHeader.setSpacingBefore(40);
            document.add(bodyHeader);

            Paragraph bodyContent = new Paragraph();
            bodyContent.setAlignment(Element.ALIGN_JUSTIFIED);
            bodyContent.add(new Phrase("Mediante la presente, solicito respetuosamente y por su intermedio, " +
                    "la designación de tribunal de defensa, ya que mi persona cumplió con todo el procedimiento y requisitos " +
                    "para la defensa del trabajo de grado titulado “ " + projectName + " ”, El mismo fue revisado por mi tutor y " +
                    "docente de taller. De tal forma, expongo a vuestra consideración para su aprobación.\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            bodyContent.setSpacingBefore(50);

            bodyContent.add(new Phrase("Con este motivo y a la espera de una respuesta positiva, me despido con las consideraciones más distinguidas.",
                    FontFactory.getFont(FontFactory.HELVETICA, 10)));
            document.add(bodyContent);

            Paragraph bodyEnd = new Paragraph();
            bodyEnd.add(new Phrase(FOOTER_TEXT, FontFactory.getFont(FontFactory.HELVETICA, 10)));
            bodyEnd.setSpacingBefore(20);
            document.add(bodyEnd);

            Paragraph footer = new Paragraph();
            bodyContent.setAlignment(Element.ALIGN_CENTER);
            footer.add(new Phrase("Univ. " + student + "\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            footer.add(new Phrase("C.I. " + studentCi + "\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            footer.setSpacingBefore(70);
            document.add(footer);

        } catch (Exception e) {
            fileName = null;
        } finally {
            document.close();
            documentSenderService.sendDocument(fileName, email, file);

        }
        return fileName;
    }

    public String generateTutorTribunalRequest(String tutor, String student, String email, String directorName, String career, String projectName, String studentCi) throws FileNotFoundException {
        String fileName = projectName + "_" + student + "_" + tutor + "_CartaConformidadTutor.pdf";
        fileName = fileName.replace(" ", "");
        Document document = new Document(PageSize.LETTER, 85, 85, 70, 70);
        File file = new File(fileName);
        try {
            OutputStream outFile = new FileOutputStream(file);
            PdfWriter.getInstance(document, outFile);
            document.open();
            createWithHeader(document, directorName, career, DOC_TITLE);

            Paragraph bodyHeader = new Paragraph();
            bodyHeader.add(new Phrase(START_TEXT, FontFactory.getFont(FontFactory.HELVETICA, 10)));
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
        } catch (Exception e) {
            fileName = null;
        } finally {
            document.close();
            documentSenderService.sendDocument(fileName, email, file);
        }
        return fileName;
    }

    public String generateSupervisorTribunalRequest(String tutor, String student, String email, String directorName, String career, String projectName, String studentCi) throws FileNotFoundException {
        String fileName = projectName + "_" + student + "_" + tutor + "_CartaConformidadTutor.pdf";
        fileName = fileName.replace(" ", "");
        Document document = new Document(PageSize.LETTER, 85, 85, 70, 70);
        File file = new File(fileName);
        try {
            OutputStream outFile = new FileOutputStream(file);
            PdfWriter.getInstance(document, outFile);
            document.open();
            createWithHeader(document, directorName, career, DOC_TITLE);

            Paragraph bodyHeader = new Paragraph();
            bodyHeader.add(new Phrase(START_TEXT, FontFactory.getFont(FontFactory.HELVETICA, 10)));
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
        } catch (Exception e) {
            fileName = null;
        } finally {
            document.close();
            documentSenderService.sendDocument(fileName, email, file);
        }
        return fileName;
    }

    public String generateTribunalApproval(String student, String email, String directorName, String career, String projectName, String tribunalName) {
        String fileName = projectName + "_" + student + "_" + tribunalName + "_CartaConformidadTribunal.pdf";
        fileName = fileName.replace(" ", "");
        Document document = new Document(PageSize.LETTER, 85, 85, 70, 70);
        File file = new File(fileName);
        try {
            OutputStream outFile = new FileOutputStream(file);
            PdfWriter.getInstance(document, outFile);
            document.open();
            createWithHeader(document, directorName, career, "REF: Proyecto final concluido\n");

            Paragraph bodyHeader = new Paragraph();
            bodyHeader.add(new Phrase(START_TEXT, FontFactory.getFont(FontFactory.HELVETICA, 10)));
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
            bodyEnd.add(new Phrase(FOOTER_TEXT, FontFactory.getFont(FontFactory.HELVETICA, 10)));
            bodyEnd.setSpacingBefore(20);
            document.add(bodyEnd);
            Paragraph footer = new Paragraph();
            footer.add(new Phrase("Lic. " + tribunalName + "\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            footer.add(new Phrase("Tribunal asignado", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            footer.setSpacingBefore(70);
            document.add(footer);
        } catch (Exception e) {
            fileName = null;
        }finally {
            document.close();
            documentSenderService.sendDocument(fileName, email, file);
        }
        return fileName;
    }

    public String generateDefenseAct(String student, String email, String president, String career, String projectName,
                                     Collection<ProjectTribunal> tribunals, String deanName, LocalDate date,
                                     LocalTime startTime, LocalTime endTime, String defensePlace, int calification) {
        String fileName = projectName + "_" + student + "_ActaDefensa.pdf";
        fileName = fileName.replace(" ", "");
        String tribunalsListed = "";
        for (ProjectTribunal tribunal : tribunals) {
            tribunalsListed += " Lic. " + tribunal.getTribunal().getFullName().toUpperCase() + ",";
        }
        Document document = new Document(PageSize.LETTER, 85, 85, 70, 70);
        File file = new File(fileName);
        try {
            OutputStream outFile = new FileOutputStream(file);
            PdfWriter.getInstance(document, outFile);
            document.open();
            createActWithHeader(document, student, president, career, tribunals);

            Paragraph bodyContent = new Paragraph();
            bodyContent.setAlignment(Element.ALIGN_JUSTIFIED);
            bodyContent.add(new Phrase(
                    "En la ciudad de Cochabamba, en fecha " + date.getDayOfMonth() + " de " +
                            getMonthName(date.getMonthValue()) + " del " + date.getYear() + "  " +
                            "a horas " + startTime.toString() + ", en " +
                            "el " + defensePlace.toUpperCase() + " de la Facultad de Ciencias y Tecnología, se efectuó" +
                            " la Presentación y Defensa del Proyecto de Grado del estudiante postulante" +
                            " " + student.toUpperCase() + ". Asistieron: Lic. " + president.toUpperCase() + ", " +
                            "como representante del señor Decano de" +
                            " la Facultad en calidad de Presidente del Tribunal Calificador y los señores: " +
                            tribunalsListed + " como Miembros del Tribunal. De acuerdo al inciso g) del Artículo 148," +
                            " Capítulo IV del Estatuto Orgánico de la Universidad Mayor de San Simón y cumplidas las condiciones " +
                            "de aprobación establecidas en el Reglamento para Defensa de Proyecto de Grado de la Carrera de Licenciatura " +
                            "en Ingeniería en " + career + ", se procedió a dicha Presentación y Defensa del Proyecto de " +
                            "Grado titulado:  ”" + projectName.toUpperCase() + "”. \n", FontFactory.getFont(FontFactory.HELVETICA, 9)));
            bodyContent.setSpacingBefore(20);
            bodyContent.add(new Phrase(
                    "Luego de la exposición, el Tribunal Calificador emitió el siguiente resultado: ", FontFactory.getFont(FontFactory.HELVETICA, 9)));
            bodyContent.setSpacingBefore(5);
            bodyContent.add(new Phrase(
                    " " + writeCalification(calification).toUpperCase() + " (" + calification + ") APROBADO CON FELICITACIONES. \n", FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
            document.add(bodyContent);

            Paragraph secondParagraph = new Paragraph();
            secondParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
            secondParagraph.add(new Phrase(
                    "Por lo tanto, el estudiante ", FontFactory.getFont(FontFactory.HELVETICA, 9)));
            secondParagraph.setSpacingBefore(5);
            secondParagraph.add(new Phrase(
                    student.toUpperCase(), FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));
            secondParagraph.add(new Phrase(", aprobó el Proyecto de titulación en " +
                    "la Carrera de Licenciatura en Ingeniería en " + career.toUpperCase() + " quedando habilitado para tramitar el Diploma " +
                    "Académico, previa aprobacion de la totalidad de las materias de la malla curricular de la Carrera." +
                    " Con lo que concluyó la Defensa Publica a horas: " + endTime.toString() + " y para su constancia firman al pie de la " +
                    "presente Acta.  ", FontFactory.getFont(FontFactory.HELVETICA, 9)));
            document.add(secondParagraph);
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            PdfPCell cell = new PdfPCell();
            Paragraph paragraph = new Paragraph();
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.add(new Phrase("______________________________\n", FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
            paragraph.add(new Phrase("Lic. " + president + " \n", FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
            paragraph.add(new Phrase("PRESIDENTE DEL TRIBUNAL", FontFactory.getFont(FontFactory.HELVETICA, 9)));
            cell.setFixedHeight(65);
            cell.addElement(paragraph);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            PdfPCell cell1;
            for (ProjectTribunal tribunal : tribunals) {
                cell1 = new PdfPCell();
                Paragraph paragraph1 = new Paragraph();
                paragraph1.setAlignment(Element.ALIGN_CENTER);
                paragraph1.add(new Phrase("______________________________\n", FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
                paragraph1.add(new Phrase("Lic. " + tribunal.getTribunal().getFullName() + " \n", FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
                paragraph1.add(new Phrase("MIEMBRO DEL TRIBUNAL", FontFactory.getFont(FontFactory.HELVETICA, 9)));
                cell1.setFixedHeight(65);
                cell1.addElement(paragraph1);
                cell1.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell1);
            }
            table.setSpacingBefore(40);
            document.add(table);

            Paragraph paragraph2 = new Paragraph();
            paragraph2.setAlignment(Element.ALIGN_CENTER);
            paragraph2.add(new Phrase("______________________________\n", FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
            paragraph2.add(new Phrase("Ing. " + deanName + " \n", FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
            paragraph2.add(new Phrase("DECANO", FontFactory.getFont(FontFactory.HELVETICA, 9)));
            document.add(paragraph2);
        } catch (Exception e) {
            fileName = null;
        }finally {
            document.close();
            documentSenderService.sendDocument(fileName, email, file);
        }
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

    private void createActWithHeader(Document document, String student, String president, String career, Collection<ProjectTribunal> tribunals) {
        Paragraph locationDate = new Paragraph();
        locationDate.setAlignment(Element.ALIGN_CENTER);
        locationDate.add(
                new Phrase("ACTA DE DEFENSA PÚBLICA", FontFactory.getFont(FontFactory.HELVETICA, 15, Font.BOLD)));
        document.add(locationDate);

        Paragraph header = new Paragraph();
        header.add(new Phrase("Estudiante: ", FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
        header.add(new Phrase(student.toUpperCase() + " \n", FontFactory.getFont(FontFactory.HELVETICA, 9)));
        header.add(new Phrase("Carrera: ", FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
        header.add(new Phrase("Ingeniería en ".toUpperCase() + career.toUpperCase() + " \n", FontFactory.getFont(FontFactory.HELVETICA, 9)));
        header.add(new Phrase("TRIBUNAL \n", FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));

        header.add(new Phrase("Presidente: ", FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
        header.add(new Phrase(president + " \n", FontFactory.getFont(FontFactory.HELVETICA, 9)));
        header.add(new Phrase("Miembros:\n", FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD)));
        for (ProjectTribunal tribunal : tribunals) {
            header.add(new Phrase("Lic." + tribunal.getTribunal().getFullName() + " \n", FontFactory.getFont(FontFactory.HELVETICA, 9)));
        }
        header.setSpacingBefore(15);
        document.add(header);

    }

    private String getMonthName(int month) {
        return months[month - 1];
    }

    private String writeCalification(int val) {
        int uVal = val % 10;
        int dVal = val / 10;

        if (val == 100) {
            return "CIEN";
        }

        if (val > 100) {
            return "";
        }

        if (val >= 10 && val <= 19) {
            return espNumber[uVal];
        } else {
            if (uVal == 0) {
                return decNumber[dVal];
            } else {
                return decNumber[dVal] + " Y " + unitNumber[uVal];
            }
        }
    }


}
