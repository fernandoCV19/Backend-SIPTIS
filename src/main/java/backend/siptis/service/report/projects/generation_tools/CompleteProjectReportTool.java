package backend.siptis.service.report.projects.generation_tools;

import backend.siptis.model.entity.editors_and_reviewers.ProjectStudent;
import backend.siptis.model.entity.project_management.Project;
import backend.siptis.model.entity.user_data.UserCareer;
import backend.siptis.utils.functions.ReportFunctions;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CompleteProjectReportTool {

    private CompleteProjectReportTool() {
    }
    public static String generateReport(List<Project> projects) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = today.format(dateTimeFormatter);
        String fileName = "reporte-completo-proyectos-" + date + ".xlsx";

        try (Workbook report = new XSSFWorkbook()) {
            Sheet sheet = report.createSheet("Reporte completo");
            Row row = sheet.createRow(2);

            String title = "Reporte completo por proyecto";
            ReportFunctions.addCellInRow(2, title, ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(3, "Fecha " + date, ReportFunctions.getContentStyle(report), row);

            row = sheet.createRow(4);

            ReportFunctions.addCellInRow(1, "N°", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(2, "Nombre", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(3, "Carrera", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(4, "Estudiantes", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(5, "Tutor", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(6, "Tribunal", ReportFunctions.getHeaderCellStyle(report), row);
            ReportFunctions.addCellInRow(7, "Modalidad", ReportFunctions.getHeaderCellStyle(report), row);

            addProjects(projects, report, 5, sheet, row);

            for (int i = 0; i < 8; i++) {
                sheet.autoSizeColumn(i);
            }


            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            report.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException exception) {
            return null;
        }
        return fileName;
    }

    private static void addProjects(List<Project> projects, Workbook report, int rowIndex, Sheet sheet, Row row) {
        for (Project project : projects) {
            String projectName = project.getName();
            String projectModality = project.getModality().getName();

            List<String> tribunals = project.getTribunals()
                    .stream()
                    .map(projectTribunal -> projectTribunal.getTribunal().getFullName()).toList();
            String tribunalNames = String.join("\n", tribunals);

            List<String> tutors = project.getTutors()
                    .stream()
                    .map(projectTutor -> projectTutor.getTutor().getFullName()).toList();
            String tutorNames = String.join("\n", tutors);

            List<String> students = project.getStudents()
                    .stream()
                    .map(projectStudent -> projectStudent.getStudent().getFullName()).toList();
            String studentNames = String.join("\n", students);

            row = sheet.createRow(rowIndex);
            ReportFunctions.addCellInRow(1, "" + (rowIndex - 4), ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(2, projectName, ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(3, getCareer(project), ReportFunctions.getContentStyle(report), row);
            ReportFunctions.addCellInRow(4, studentNames, ReportFunctions.getListStyle(report), row);
            ReportFunctions.addCellInRow(5, tutorNames, ReportFunctions.getListStyle(report), row);
            ReportFunctions.addCellInRow(6, tribunalNames, ReportFunctions.getListStyle(report), row);
            ReportFunctions.addCellInRow(7, projectModality, ReportFunctions.getContentStyle(report), row);
            rowIndex++;

        }
    }

    private static boolean isCareer(Project project, backend.siptis.commons.UserCareer compareCareer) {
        Collection<ProjectStudent> students = project.getStudents();
        boolean isCareer = true;

        for (ProjectStudent student : students) {
            Set<UserCareer> studentCareer = student.getStudent().getCareer();
            for (UserCareer career : studentCareer) {
                isCareer = isCareer && career.getName().equals(compareCareer.name());
            }
        }
        return isCareer;
    }

    private static String getCareer(Project project) {
        String career = "COMPARTIDO";
        if (isCareer(project, backend.siptis.commons.UserCareer.SISTEMAS))
            career = backend.siptis.commons.UserCareer.SISTEMAS.name();
        else if (isCareer(project, backend.siptis.commons.UserCareer.INFORMATICA))
            career = backend.siptis.commons.UserCareer.INFORMATICA.name();
        return career;
    }
}
