package group.streetwear.trapp.timeRecord;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TimeRecordRepository extends CrudRepository<TimeRecord, Long> {

    public TimeRecord findOneByWorkStartTimeGreaterThanAndWorkStartTimeLessThanAndUsername(LocalDateTime startDate, LocalDateTime endDate, String username);

    public List<TimeRecord> findByUsername(String username);

    @Query(value = "from TimeRecord w where w.workStartTime BETWEEN :startDate AND :endDate")
    public List<TimeRecord> findAllBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query(value = "from TimeRecord w where (w.username LIKE :username) AND (w.workStartTime BETWEEN :startDate AND :endDate)")
    public List<TimeRecord> findAllBetweenDatesByUsername(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("username") String username);

}
