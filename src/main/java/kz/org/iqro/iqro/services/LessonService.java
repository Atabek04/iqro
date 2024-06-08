package kz.org.iqro.iqro.services;

import kz.org.iqro.iqro.entities.Lesson;
import kz.org.iqro.iqro.repositories.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public List<Lesson> getAllLessonsByModuleId(long moduleId) {
        return lessonRepository.findAllByModuleId(moduleId);
    }

    public void saveLesson(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public Lesson getLessonById(int lessonId) {
        return lessonRepository.findById(lessonId).orElse(null);
    }

    public void deleteLesson(int lessonId) {
        lessonRepository.deleteById(lessonId);
    }
}
