package backend.siptis.model.repository.semester;

import backend.siptis.model.entity.semester.SemesterInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface SemesterInformationRepository extends JpaRepository<SemesterInformation, Long> {



    boolean existsSemesterInformationByInProgressIsTrue();


    @Query(value ="SELECT * " +
            "FROM user_information ui " +
            "WHERE ui.in_progress = true ORDER BY end_date DESC LIMIT 1", nativeQuery = true)
    Optional<SemesterInformation> findActiveSemester();

    Optional<SemesterInformation> findById(long id);


}
