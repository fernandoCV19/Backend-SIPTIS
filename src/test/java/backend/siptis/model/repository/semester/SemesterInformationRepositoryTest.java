package backend.siptis.model.repository.semester;

import backend.siptis.model.entity.semester.SemesterInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SemesterInformationRepositoryTest {
    @Autowired
    private SemesterInformationRepository semesterInformationRepository;
    private SemesterInformation semesterInformation;

    @BeforeEach
    void createSemester() {
        semesterInformation = new SemesterInformation();
        semesterInformation.setInProgress(true);
        semesterInformation.setPeriod("2-2023");
        semesterInformation.setEndDate(LocalDate.now());
    }

    private SemesterInformation createNewSemester() {
        SemesterInformation newSemester = new SemesterInformation();
        newSemester.setInProgress(true);
        newSemester.setPeriod("2-2023");
        newSemester.setEndDate(LocalDate.now());
        return newSemester;
    }

    @Test
    @DisplayName("Test for find first by in progress true and id order by end date desc")
    void givenSemesterInformation_whenFindFirstByInProgressTrueOrderByEndDateDesc_thenNull() {
        assertTrue(semesterInformationRepository.findFirstByInProgressTrueOrderByEndDateDesc().isEmpty());
    }

    @Test
    @DisplayName("Test for get current period from semester")
    void givenSemesterInformation_whenGetCurrentPeriod_thenPeriod() {
        semesterInformationRepository.save(semesterInformation);
        String period = semesterInformationRepository.getCurrentPeriod();
        assertNotNull(period);
        assertEquals("2-2023", period);
    }

    @Test
    @DisplayName("Test for get current period from non existing semester")
    void givenSemesterInformation_whenGetCurrentPeriod_thenNull() {
        String period = semesterInformationRepository.getCurrentPeriod();
        assertNull(period);
    }

    @Test
    @DisplayName("Test for find first by in progress true and id order by end date desc")
    void givenSemesterInformation_whenFindFirstByInProgressTrueAndIdOrderByEndDateDesc_thenNull() {
        assertTrue(semesterInformationRepository.findFirstByInProgressTrueAndIdOrderByEndDateDesc(123456L).isEmpty());
    }

    @Test
    @DisplayName("Test for verify if exist semester in progress")
    void givenSemesterInformation_whenExistsSemesterInformationByInProgressIsTrue_thenTrue() {
        semesterInformationRepository.save(semesterInformation);
        assertTrue(semesterInformationRepository.existsSemesterInformationByInProgressIsTrue());
    }

    @Test
    @DisplayName("Test for verify if exist non existing semester in progress")
    void givenSemesterInformation_whenExistsSemesterInformationByInProgressIsTrue_thenFalse() {
        assertFalse(semesterInformationRepository.existsSemesterInformationByInProgressIsTrue());
    }

    @Test
    @DisplayName("Test for find semester by id")
    void givenSemesterInformationId_whenFindById_thenSemesterInformation() {
        semesterInformationRepository.save(semesterInformation);
        assertFalse(semesterInformationRepository.findById(semesterInformation.getId()).isEmpty());
    }

    @Test
    @DisplayName("Test for find semester by non existing id")
    void givenSemesterInformationId_whenFindById_thenSemesterNull() {
        assertTrue(semesterInformationRepository.findById(123L).isEmpty());
    }

    @Test
    @DisplayName("Test for verify if exist semester information by id")
    void givenSemesterInformationId_whenExistsSemesterInformationById_thenSemesterTrue() {
        semesterInformationRepository.save(semesterInformation);
        assertTrue(semesterInformationRepository.existsSemesterInformationById(semesterInformation.getId()));
    }

    @Test
    @DisplayName("Test for verify if exist semester information by non existing id")
    void givenSemesterInformationId_whenExistsSemesterInformationById_thenSemesterFalse() {
        assertFalse(semesterInformationRepository.existsSemesterInformationById(123L));
    }


}
