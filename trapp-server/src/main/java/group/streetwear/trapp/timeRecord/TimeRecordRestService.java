package group.streetwear.trapp.timeRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/timeRecord")
public class TimeRecordRestService {

    @Autowired
    TimeRecordRepository timeRecordRepository;

//    @PreAuthorize("hasAuthority('" + Authority.USER + "')")
    @GetMapping(produces = "application/json")
    public List<TimeRecord> getTimeRecord() {
        return findTimeRecordById();
    }

    private List<TimeRecord> findTimeRecordById() {
        List<TimeRecord> timeRecords = new ArrayList<>();
        timeRecordRepository.findAll().forEach(timeRecords::add);
        return timeRecords;
    }
}
