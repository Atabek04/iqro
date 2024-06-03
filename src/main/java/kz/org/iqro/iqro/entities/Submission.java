package kz.org.iqro.iqro.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "submission")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Long data;
    private Integer grade;
    private Timestamp date;
    private Short status;

    public Submission() {}

    public Submission(Long id, Assignment assignment, User user, Long data, Integer grade, Timestamp date, Short status) {
        this.id = id;
        this.assignment = assignment;
        this.user = user;
        this.data = data;
        this.grade = grade;
        this.date = date;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getData() {
        return data;
    }

    public void setData(Long data) {
        this.data = data;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}

