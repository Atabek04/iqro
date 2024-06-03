package kz.org.iqro.iqro.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "lesson_comment")
public class LessonComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String text;
    private Timestamp date;

    public LessonComment() {}

    public LessonComment(Long id, Lesson lesson, User user, String text, Timestamp date) {
        this.id = id;
        this.lesson = lesson;
        this.user = user;
        this.text = text;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}

