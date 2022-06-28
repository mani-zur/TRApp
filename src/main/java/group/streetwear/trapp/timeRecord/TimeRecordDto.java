package group.streetwear.trapp.timeRecord;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import static java.util.Objects.nonNull;

public class TimeRecordDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "This field can't be empty!")
    private LocalDate date;
    @DateTimeFormat(pattern = "HH:mm")
    @NotNull(message = "This field can't be empty!")
    private LocalTime start;
    @DateTimeFormat(pattern = "HH:mm")
    @NotNull(message = "This field can't be empty!")
    private LocalTime end;

    private Duration workTime;

    public TimeRecordDto(LocalDate date, LocalTime start, LocalTime end) {
        this.date = date;
        this.start = start;
        this.end = end;
        if (nonNull(start) && nonNull(end)) {
            this.workTime = Duration.between(start, end);
        }
    }


    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public Duration getWorkTime() {
        return workTime;
    }
}
