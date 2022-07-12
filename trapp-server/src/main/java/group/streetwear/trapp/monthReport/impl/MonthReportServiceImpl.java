package group.streetwear.trapp.monthReport.impl;

import group.streetwear.trapp.monthReport.MonthReport;
import group.streetwear.trapp.monthReport.MonthReportService;
import group.streetwear.trapp.timeRecord.TimeRecord;
import group.streetwear.trapp.timeRecord.TimeRecordDto;
import group.streetwear.trapp.timeRecord.TimeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class MonthReportServiceImpl implements MonthReportService {

    @Autowired
    private TimeRecordRepository timeRecordRepository;

    private static TimeRecordDto parseTimeRecordDto(TimeRecord record) {
        return new TimeRecordDto(
                record.getId(),
                record.getWorkStartTime().toLocalDate(),
                record.getWorkStartTime().toLocalTime(),
                record.getWorkEndTime().toLocalTime()
        );
    }

    public List<MonthReport> getAllMonthReport(LocalDate month) {
        LocalDateTime monthBegin = month.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay();
        LocalDateTime monthEnd = month.with(TemporalAdjusters.lastDayOfMonth()).atTime(LocalTime.MAX);
        List<TimeRecord> workTimes = this.timeRecordRepository.findAllBetweenDates(monthBegin, monthEnd);

        Map<String, List<TimeRecord>> groupedRecords = workTimes.stream().collect(groupingBy(TimeRecord::getUsername, Collectors.toList()));


        List<MonthReport> reports = new ArrayList<>();
        groupedRecords.forEach((username, reportedTime) -> {
            List<TimeRecordDto> dtos = reportedTime.stream().map(MonthReportServiceImpl::parseTimeRecordDto).collect(Collectors.toList());
            reports.add(new MonthReport(username, dtos));
        });

        return reports;
    }
}
