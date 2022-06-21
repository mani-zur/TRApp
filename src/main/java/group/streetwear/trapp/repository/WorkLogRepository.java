package group.streetwear.trapp.repository;

import group.streetwear.trapp.model.WorkLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkLogRepository extends CrudRepository<WorkLog, Long> {

    public List<WorkLog> findByUsername(String username);

    @Query(value = "from WorkLog w where w.workStartTime BETWEEN :startDate AND :endDate")
    public List<WorkLog> getAllBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
