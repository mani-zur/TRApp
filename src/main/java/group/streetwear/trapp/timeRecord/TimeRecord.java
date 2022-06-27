package group.streetwear.trapp.timeRecord;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="TIME_RECORDS")
public class TimeRecord {

    @Id
    @GeneratedValue
    private Long Id;

    @Column( name = "USERNAME", nullable = false)
    private String username;

    @Column( name = "WORK_START_TIME", nullable = false)
    private LocalDateTime workStartTime;

    @Column( name = "WORK_END_TIME", nullable = false)
    private LocalDateTime workEndTime;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    public TimeRecord() {
    }

    public Long getId() {
        return Id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getWorkStartTime() {
        return workStartTime;
    }

    public void setWorkStartTime(LocalDateTime workStartTime) {
        this.workStartTime = workStartTime;
    }

    public LocalDateTime getWorkEndTime() {
        return workEndTime;
    }

    public void setWorkEndTime(LocalDateTime workEndTime) {
        this.workEndTime = workEndTime;
    }
}
