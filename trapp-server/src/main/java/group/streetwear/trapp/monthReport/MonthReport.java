package group.streetwear.trapp.monthReport;

import group.streetwear.trapp.timeRecord.TimeRecordDto;

import java.time.Duration;
import java.util.List;

public class MonthReport {
    private String username;
    private List<TimeRecordDto> timeRecords;

    public MonthReport(String username, List<TimeRecordDto> timeRecords){
        this.username = username;
        this.timeRecords = timeRecords;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<TimeRecordDto> getTimeRecords() {
        return timeRecords;
    }

    public void setTimeRecords(List<TimeRecordDto> timeRecords) {
        this.timeRecords = timeRecords;
    }

    public Duration getWorkTimeSum() {
        Duration workTimeSum = Duration.ZERO;
        for (TimeRecordDto timeRecord : this.timeRecords) {
            workTimeSum = workTimeSum.plus(timeRecord.getWorkTime());
        }
        return workTimeSum;
    }
}
