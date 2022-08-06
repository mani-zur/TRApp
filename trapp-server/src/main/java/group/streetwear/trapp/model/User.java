package group.streetwear.trapp.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue
    private Long Id;

    @Column(name = "AD_GUID", nullable = false)
    private String activeDirectoryGUID;

    @Column(name = "SALARY")
    private Float salary;

    @Column(name = "EMPLOYMENT_DATE")
    private LocalDate employmentDate;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;


    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public String getActiveDirectoryGUID() {
        return activeDirectoryGUID;
    }

    public void setActiveDirectoryGUID(String activeDirectoryGUID) {
        this.activeDirectoryGUID = activeDirectoryGUID;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public Long getId() {
        return Id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}