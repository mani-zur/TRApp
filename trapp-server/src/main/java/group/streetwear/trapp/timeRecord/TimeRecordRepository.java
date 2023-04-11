package group.streetwear.trapp.timeRecord;

import group.streetwear.trapp.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TimeRecordRepository extends CrudRepository<TimeRecord, Long> {

    public TimeRecord findOneByWorkStartTimeGreaterThanAndWorkStartTimeLessThanAndUser(LocalDateTime startDate, LocalDateTime endDate, User user);

    public List<TimeRecord> findByUser(User user);

    @Query(value = "from TimeRecord w where w.workStartTime BETWEEN :startDate AND :endDate")
    public List<TimeRecord> findAllBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query(value = "from TimeRecord w where (w.user = :user) AND (w.workStartTime BETWEEN :startDate AND :endDate)")
    public List<TimeRecord> findAllBetweenDatesByUser(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("user") User user);

}

