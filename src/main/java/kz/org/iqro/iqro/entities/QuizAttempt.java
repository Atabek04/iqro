package kz.org.iqro.iqro.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "quiz_attempt")
public class QuizAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    private Timestamp date;

    public QuizAttempt() {}

    public QuizAttempt(Long id, User user, Quiz quiz, Timestamp date) {
        this.id = id;
        this.user = user;
        this.quiz = quiz;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}

