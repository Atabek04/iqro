package kz.org.iqro.iqro.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "question_option")
public class QuestionOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private Boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public QuestionOption() {}

    public QuestionOption(Long id, String text, Boolean isCorrect, Question question) {
        this.id = id;
        this.text = text;
        this.isCorrect = isCorrect;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}

