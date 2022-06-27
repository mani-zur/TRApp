package group.streetwear.trapp.reporting;

import group.streetwear.trapp.timeRecord.TimeRecord;
import group.streetwear.trapp.timeRecord.TimeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("api/worklog")
public class ReportingApiController {

    @Autowired
    TimeRecordRepository timeRecordRepository;

    @GetMapping(value = "/", produces = "application/json")
    public @ResponseBody List<TimeRecord> getAllWorkLogs() {
        List<TimeRecord> result =
                StreamSupport.stream(this.timeRecordRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList());
        return result;
    }

    @GetMapping(value = "/user/{username}", produces = "application/json")
    public @ResponseBody List<TimeRecord> getAllWorkLogsForUser(@PathVariable String username) {
        return timeRecordRepository.findByUsername(username);
    }

    @GetMapping(value = "/between/{startDate}/{endDate}", produces = "application/json")
    public @ResponseBody List<TimeRecord> getAllBetweenDate(@PathVariable String startDate, @PathVariable String endDate) {
        return timeRecordRepository.findAllBetweenDates(LocalDateTime.parse(startDate), LocalDateTime.parse(endDate));
    }
}
