package backend.siptis.model.repository.notifications;

import backend.siptis.model.entity.notifications.GeneralActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface GeneralActivityRepository extends JpaRepository<GeneralActivity, Long> {
    @Query("SELECT ga FROM GeneralActivity ga WHERE ga.activityDate >=:now ")
    Page<GeneralActivity> findAllAfterADate(@Param("now") LocalDate now, Pageable pageable);

}
