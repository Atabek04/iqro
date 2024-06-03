package kz.org.iqro.iqro.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "module")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "module")
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "module")
    private List<Quiz> quizzes;

    @OneToMany(mappedBy = "module")
    private List<Assignment> assignments;

    public Module() {}

    public Module(Long id, String name, Course course, List<Lesson> lessons, List<Quiz> quizzes, List<Assignment> assignments) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.lessons = lessons;
        this.quizzes = quizzes;
        this.assignments = assignments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
}
