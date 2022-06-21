package group.streetwear.trapp.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="WORK_LOG")
public class WorkLog {

    @Id
    @GeneratedValue
    private Long Id;

    @Column( name = "USERNAME", nullable = false)
    private String username;

    @Column( name = "WORK_START_TIME", nullable = false)
    private LocalDateTime workStartTime;

    @Column( name = "WORK_END_TIME", nullable = false)
    private LocalDateTime workEndTime;
    public WorkLog() {
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
