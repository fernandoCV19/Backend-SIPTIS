package backend.siptis.model.repository.semester;

import backend.siptis.model.entity.semester.SemesterInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SemesterInformationRepository extends JpaRepository<SemesterInformation, Long> {

    boolean existsSemesterInformationByInProgressIsTrue();

    Optional<SemesterInformation> findFirstByInProgressTrueOrderByEndDateDesc();

    @Query(value = "SELECT si.period " +
            "FROM semester_information si " +
            "WHERE si.in_progress = true ORDER BY end_date DESC LIMIT 1", nativeQuery = true)
    String getCurrentPeriod();

    Optional<SemesterInformation> findFirstByInProgressTrueAndIdOrderByEndDateDesc(Long id);

    Optional<SemesterInformation> findById(long id);

    boolean existsSemesterInformationById(Long id);


}
