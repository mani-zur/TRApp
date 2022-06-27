package group.streetwear.trapp.timeRecord;

import java.time.LocalDate;
import java.util.List;

public interface TimeRecordService {

    List<TimeRecordDto> getAllForUser(String username);

    List<TimeRecordDto> getAllForUserInMonth(String username, LocalDate month);

    void saveSingleRecord(TimeRecordDto recordDto, String username);

}
