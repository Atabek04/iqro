package kz.org.iqro.iqro.repositories;

import kz.org.iqro.iqro.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    List<Lesson> findAllByModuleId(long moduleId);
}
