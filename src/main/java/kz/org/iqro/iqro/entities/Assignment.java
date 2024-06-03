package kz.org.iqro.iqro.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "assignment")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Timestamp deadline;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    @OneToMany(mappedBy = "assignment")
    private List<Submission> submissions;

    @OneToMany(mappedBy = "assignment")
    private List<AssignmentComment> comments;

    public Assignment() {}

    public Assignment(Long id, String title, Timestamp deadline, Module module, List<Submission> submissions, List<AssignmentComment> comments) {
        this.id = id;
        this.title = title;
        this.deadline = deadline;
        this.module = module;
        this.submissions = submissions;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    public List<AssignmentComment> getComments() {
        return comments;
    }

    public void setComments(List<AssignmentComment> comments) {
        this.comments = comments;
    }
}

