package group.streetwear.trapp.timeRecord;


import group.streetwear.trapp.model.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", updatable = false)
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
