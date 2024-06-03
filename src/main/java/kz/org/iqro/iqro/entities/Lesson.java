package kz.org.iqro.iqro.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String videoLink;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    @OneToMany(mappedBy = "lesson")
    private List<LessonComment> comments;

    public Lesson() {}

    public Lesson(Long id, String title, String videoLink, String notes, Module module, List<LessonComment> comments) {
        this.id = id;
        this.title = title;
        this.videoLink = videoLink;
        this.notes = notes;
        this.module = module;
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

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<LessonComment> getComments() {
        return comments;
    }

    public void setComments(List<LessonComment> comments) {
        this.comments = comments;
    }
}

