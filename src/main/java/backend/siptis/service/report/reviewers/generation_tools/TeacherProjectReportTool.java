package backend.siptis.service.report.reviewers.generation_tools;

import backend.siptis.commons.Modality;
import backend.siptis.model.entity.editors_and_reviewers.ProjectTeacher;
import backend.siptis.model.entity.project_management.Project;
import backend.siptis.utils.functions.ReportFunctions;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class TeacherProjectReportTool {

    private static int rowIndex = 4;
    private static int projectCounter = 1;

    public static String generateReport(List<ProjectTeacher> toReview, List<ProjectTeacher> reviewed, List<ProjectTeacher> accepted) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = today.format(dateTimeFormatter);
        String fileName = "Report-teacher" + date + ".xlsx";

        try (Workbook report = new XSSFWorkbook()) {
            CellStyle headerStyle = ReportFunctions.getHeaderCellStyle(report);
            CellStyle contentStyle = ReportFunctions.getContentStyle(report);
            Sheet sheet = report.createSheet("Proyectos");

            addHeadersValues(sheet, date);
            addToReview(sheet, contentStyle, headerStyle, toReview);
            addReviewed(sheet, contentStyle, headerStyle, reviewed);
            addAccepted(sheet, contentStyle, headerStyle, accepted);

            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            report.write(fileOutputStream);
            fileOutputStream.close();
            return fileName;
        } catch (IOException exception) {
            return null;
        }
    }

    private static void addBody(Sheet sheet, CellStyle contentStyle, CellStyle headerStyle, List<ProjectTeacher> projects) {
        Row row = sheet.createRow(rowIndex);
        ReportFunctions.addCellInRow(1, "N°", headerStyle, row);
        ReportFunctions.addCellInRow(2, "Proyecto", headerStyle, row);
        ReportFunctions.addCellInRow(3, "Modalidad", headerStyle, row);
        ReportFunctions.addCellInRow(4, "Docentes", headerStyle, row);
        ReportFunctions.addCellInRow(5, "Tutores", headerStyle, row);
        ReportFunctions.addCellInRow(6, "Supervisores", headerStyle, row);
        ReportFunctions.addCellInRow(7, "Tribunales", headerStyle, row);
        rowIndex++;

        for (ProjectTeacher projectTeacher : projects) {
            String project = projectTeacher.getProject().getName();
            String modality = projectTeacher.getProject().getModality().getName();
            String teachers = getTeachers(projectTeacher.getProject());
            String tutors = getTutors(projectTeacher.getProject());
            String supervisors = getSupervisors(projectTeacher.getProject());
            String tribunals = getTribunal(projectTeacher.getProject());

            row = sheet.createRow(rowIndex);

            ReportFunctions.addCellInRow(1, String.valueOf(projectCounter), contentStyle, row);
            ReportFunctions.addCellInRow(2, project, contentStyle, row);
            ReportFunctions.addCellInRow(3, modality, contentStyle, row);
            ReportFunctions.addCellInRow(4, teachers, contentStyle, row);
            ReportFunctions.addCellInRow(5, tutors, contentStyle, row);
            ReportFunctions.addCellInRow(6, supervisors, contentStyle, row);
            ReportFunctions.addCellInRow(7, tribunals, contentStyle, row);
            rowIndex++;
            projectCounter++;
        }
        for (int i = 0; i < 8; i++) {
            sheet.autoSizeColumn(i);
        }
    }


    private static void addAccepted(Sheet sheet, CellStyle contentStyle, CellStyle headerStyle, List<ProjectTeacher> accepted) {
        Row row = sheet.createRow(rowIndex);
        ReportFunctions.addCellInRow(1, "PROYECTOS ACEPTADOS", headerStyle, row);
        rowIndex++;

        addBody(sheet, contentStyle, headerStyle, accepted);

    }

    private static void addReviewed(Sheet sheet, CellStyle contentStyle, CellStyle headerStyle, List<ProjectTeacher> reviewed) {
        Row row = sheet.createRow(rowIndex);
        ReportFunctions.addCellInRow(1, "PROYECTOS REVISADOS", headerStyle, row);
        rowIndex++;

        addBody(sheet, contentStyle, headerStyle, reviewed);
    }

    private static void addToReview(Sheet sheet, CellStyle contentStyle, CellStyle headerStyle, List<ProjectTeacher> toReview) {
        Row row = sheet.createRow(rowIndex);
        ReportFunctions.addCellInRow(1, "PROYECTOS POR REVISAR", headerStyle, row);
        rowIndex++;

        addBody(sheet, contentStyle, headerStyle, toReview);
    }

    private static String getTribunal(Project project) {
        return project.getTribunals()
                .stream()
                .map(pt -> pt.getTribunal().getFullName())
                .collect(Collectors.joining(", "));
    }

    private static String getSupervisors(Project project) {
        return project.getModality().getName().equals(Modality.ADSCRIPCION.toString()) ?
                project.getSupervisors()
                        .stream()
                        .map(ps -> ps.getSupervisor().getFullName())
                        .collect(Collectors.joining(", ")) : "NO APLICA";
    }

    private static String getTutors(Project project) {
        return project.getTutors()
                .stream()
                .map(pt -> pt.getTutor().getFullName())
                .collect(Collectors.joining(", "));
    }

    private static String getTeachers(Project project) {
        return project.getTeachers()
                .stream()
                .map(pt -> pt.getTeacher().getFullName())
                .collect(Collectors.joining(", "));
    }


    private static void addHeadersValues(Sheet sheet, String date) {
        Row row = sheet.createRow(2);
        ReportFunctions.addCellInRow(4, "Reporte general de defensas", null, row);
        ReportFunctions.addCellInRow(6, "Fecha: " + date, null, row);
    }
}
