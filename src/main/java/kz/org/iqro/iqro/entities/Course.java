package kz.org.iqro.iqro.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Timestamp lastUpdatedAt;
    private Short status;
    @Column(name = "image_link")
    private String imageLink;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Module> modules = new ArrayList<>();

    @Transient
    private int progressPercentage;

    @Transient
    private Lesson firstUncompletedLesson;

    @Transient
    private Lesson lastLesson;

    public Course(Long courseId) {
        this.id = courseId;
    }

    public int getTotalLessons() {
        return modules.stream().mapToInt(Module::getTotalLessons).sum();
    }

}
