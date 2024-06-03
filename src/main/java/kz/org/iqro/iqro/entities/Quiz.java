package kz.org.iqro.iqro.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    @OneToMany(mappedBy = "quiz")
    private List<Question> questions;

    public Quiz() {}

    public Quiz(Long id, String title, Module module, List<Question> questions) {
        this.id = id;
        this.title = title;
        this.module = module;
        this.questions = questions;
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

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
