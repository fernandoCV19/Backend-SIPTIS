package backend.siptis.model.repository.semester;

import backend.siptis.model.entity.semester.SemesterInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface SemesterInformationRepository extends JpaRepository<SemesterInformation, Long> {


    boolean existsSemesterInformationByInProgressIsTrue();

    @Query(value ="SELECT * " +
            "FROM semester_information si " +
            "WHERE si.in_progress = true ORDER BY end_date DESC LIMIT 1", nativeQuery = true)
    Optional<SemesterInformation> findActiveSemester();

    @Query(value ="SELECT si.period " +
            "FROM semester_information si " +
            "WHERE si.in_progress = true ORDER BY end_date DESC LIMIT 1", nativeQuery = true)
    String getCurrentPeriod();


    @Query(value ="SELECT * " +
            "FROM semester_information si " +
            "WHERE si.in_progress = true " +
            "AND si.id = :id ORDER BY end_date DESC LIMIT 1", nativeQuery = true)
    Optional<SemesterInformation> findActiveSemesterById(Long id);


    Optional<SemesterInformation> findById(long id);

    boolean existsSemesterInformationById(Long id);


}
