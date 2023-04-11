package group.streetwear.trapp.timeRecord;

import group.streetwear.trapp.model.User;

import java.time.LocalDate;
import java.util.List;

public interface TimeRecordService {

    List<TimeRecordDto> getAllForUser(User user);

    List<TimeRecordDto> getAllForUserInMonth(User user, LocalDate month);

    void saveSingleRecord(TimeRecordDto recordDto, User user);

}
