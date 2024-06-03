package kz.org.iqro.iqro.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "quiz_result")
public class QuizResults {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "attempt_id")
    private QuizAttempt attempt;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "selected_option_id")
    private QuestionOption selectedOption;

    private Boolean isCorrect;

    public QuizResults() {}

    public QuizResults(Long id, QuizAttempt attempt, Question question, QuestionOption selectedOption, Boolean isCorrect) {
        this.id = id;
        this.attempt = attempt;
        this.question = question;
        this.selectedOption = selectedOption;
        this.isCorrect = isCorrect;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuizAttempt getAttempt() {
        return attempt;
    }

    public void setAttempt(QuizAttempt attempt) {
        this.attempt = attempt;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public QuestionOption getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(QuestionOption selectedOption) {
        this.selectedOption = selectedOption;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }
}

