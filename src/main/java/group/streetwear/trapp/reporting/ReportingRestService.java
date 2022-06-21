package group.streetwear.trapp.reporting;

import group.streetwear.trapp.model.WorkLog;
import group.streetwear.trapp.repository.WorkLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/worklog")
public class ReportingRestService {

    @Autowired
    WorkLogRepository workLogRepository;

    @GetMapping(value = "/", produces = "application/json")
    public @ResponseBody List<WorkLog> getAllWorkLogs() {
        return new ArrayList<>();
    }

    @GetMapping(value = "/user/{username}", produces = "application/json")
    public @ResponseBody List<WorkLog> getAllWorkLogsForUser(@PathVariable String username) {
        return new ArrayList<>();
    }

    @GetMapping(value = "/between/{startDate}/{endDate}", produces = "application/json")
    public @ResponseBody List<WorkLog> getAllBetweenDate(@PathVariable String startDate, @PathVariable String endDate) {
        return workLogRepository.getAllBetweenDates(LocalDateTime.parse(startDate), LocalDateTime.parse(endDate));
    }
}
