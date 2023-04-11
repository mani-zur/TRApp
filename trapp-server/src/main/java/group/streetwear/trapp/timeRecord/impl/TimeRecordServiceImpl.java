package group.streetwear.trapp.timeRecord.impl;

import group.streetwear.trapp.model.User;
import group.streetwear.trapp.timeRecord.TimeRecord;
import group.streetwear.trapp.timeRecord.TimeRecordDto;
import group.streetwear.trapp.timeRecord.TimeRecordRepository;
import group.streetwear.trapp.timeRecord.TimeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class TimeRecordServiceImpl implements TimeRecordService {

    @Autowired
    TimeRecordRepository timeRecordRepository;

    private static TimeRecord parseTimeRecord(TimeRecordDto recordDto) {
        LocalDateTime startTime = recordDto.getStart().atDate(recordDto.getDate());
        LocalDateTime endTime = recordDto.getEnd().atDate(recordDto.getDate());
        TimeRecord timeRecord = new TimeRecord();
        timeRecord.setWorkStartTime(startTime);
        timeRecord.setWorkEndTime(endTime);
        return timeRecord;
    }

    private static TimeRecordDto parseTimeRecordDto(TimeRecord record) {
        return new TimeRecordDto(
                record.getId(),
                record.getWorkStartTime().toLocalDate(),
                record.getWorkStartTime().toLocalTime(),
                record.getWorkEndTime().toLocalTime()
        );
    }

    public List<TimeRecordDto> getAllForUser(User user) {
        List<TimeRecord> workTimes = this.timeRecordRepository.findByUser(user);
        return workTimes.stream().map(TimeRecordServiceImpl::parseTimeRecordDto).collect(Collectors.toList());
    }

    public List<TimeRecordDto> getAllForUserInMonth(User user, LocalDate month) {
        LocalDateTime monthBegin = month.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay();
        LocalDateTime monthEnd = month.with(TemporalAdjusters.lastDayOfMonth()).atTime(LocalTime.MAX);
        List<TimeRecord> workTimes = this.timeRecordRepository.findAllBetweenDatesByUser(monthBegin, monthEnd, user);
        return workTimes.stream().map(TimeRecordServiceImpl::parseTimeRecordDto).collect(Collectors.toList());
    }

    public void saveSingleRecord(TimeRecordDto timeRecordDto, User user) {
        TimeRecord timeRecordToSave = timeRecordRepository.findOneByWorkStartTimeGreaterThanAndWorkStartTimeLessThanAndUser(
                timeRecordDto.getDate().atStartOfDay(), timeRecordDto.getDate().atTime(LocalTime.MAX), user
        );

        if (isNull(timeRecordToSave)) {
            timeRecordToSave = new TimeRecord();
            timeRecordToSave.setUser(user);
        }

        LocalDateTime startTime = timeRecordDto.getStart().atDate(timeRecordDto.getDate());
        LocalDateTime endTime = timeRecordDto.getEnd().atDate(timeRecordDto.getDate());
        timeRecordToSave.setWorkStartTime(startTime);
        timeRecordToSave.setWorkEndTime(endTime);

        this.timeRecordRepository.save(timeRecordToSave);
    }
}
